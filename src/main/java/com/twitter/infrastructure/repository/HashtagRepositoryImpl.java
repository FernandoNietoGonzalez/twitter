package com.twitter.infrastructure.repository;

import com.twitter.domain.exception.InvalidDataException;
import com.twitter.domain.exception.InvalidTweetException;
import com.twitter.domain.model.Hashtag;
import com.twitter.domain.model.filter.HashtagFilter;
import com.twitter.domain.port.output.HashtagRepository;
import com.twitter.infrastructure.repository.adapter.HashtagRepositoryAdapter;
import com.twitter.infrastructure.repository.criteria.HashtagCriteriaRepository;
import com.twitter.infrastructure.repository.entity.HashtagEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Component
public class HashtagRepositoryImpl implements HashtagRepository {

    @Autowired
    private HashtagJpaRepository repository;

    @Autowired
    private HashtagCriteriaRepository criteriaRepository;

    private HashtagRepositoryAdapter adapter = HashtagRepositoryAdapter.INSTANCE;

    @Override
    public List<Hashtag> getHashtagsByFilter(final HashtagFilter filter) {
        try {
            log.debug("Finding hashtags by filter {}", filter);
            final List<Hashtag> result = criteriaRepository.getHashtagsByFiler(filter)
                    .stream()
                    .map(hashtagEntity -> adapter.adapt(hashtagEntity))
                    .collect(Collectors.toList());
            log.debug("Return {} hashtags by filter {}", result.size(), filter);
            return result;
        } catch (final Exception e) {
            final String error = format("Unexpected error finding hashtags by filter %s: %s", filter, e.getMessage());
            log.error(error);
            throw new InvalidDataException(error);
        }
    }

    @Transactional
    @Override
    public void saveHashtags(final List<Hashtag> hashtags) {
        try {
            hashtags.forEach(hashtag -> {
                log.debug("Creating hashtag with text : {}", hashtag.getText());
                final Hashtag hashtagEntity = getHashtagByText(hashtag.getText());
                if (hashtagEntity != null) {
                    hashtag.merge(hashtagEntity, hashtag);
                }
                final HashtagEntity entity = adapter.adaptToRepo(hashtag);
                repository.save(entity);
                log.debug("Created hashtag with text : {}", entity.getText());
            });
        } catch (final Exception e) {
            log.error("Error creating hashtags");
            throw new InvalidTweetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Hashtag getHashtagByText(final String text) {
        final Optional<HashtagEntity> entity = repository.findByText(text);
        if (!entity.isPresent()) {
            final String error = format("Hashtag not found by text %s", text);
            log.error(error);
            return null;
        }
        return entity.map(tweet -> adapter.adapt(tweet)).get();
    }
}
