package com.twitter.infrastructure.repository;

import com.twitter.domain.exception.InvalidTweetException;
import com.twitter.domain.model.Tweet;
import com.twitter.domain.model.filter.TweetFilter;
import com.twitter.domain.port.output.TweetRepository;
import com.twitter.infrastructure.repository.adapter.TweetRepositoryAdapter;
import com.twitter.infrastructure.repository.criteria.TweetCriteriaRepository;
import com.twitter.infrastructure.repository.entity.TweetEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@Slf4j
public class TweetRepositoryImpl implements TweetRepository {

    @Autowired
    private TweetJpaRepository repository;

    @Autowired
    private TweetCriteriaRepository tweetCriteriaRepository;

    private TweetRepositoryAdapter adapter = TweetRepositoryAdapter.INSTANCE;

    @Transactional
    @Override
    public List<Tweet> getTweetsByFilter(final TweetFilter filter) {
        try {
            log.debug("Finding tweets by filter {}", filter);
            final List<Tweet> result = tweetCriteriaRepository.getTweetsByFiler(filter)
                    .stream()
                    .map(tweet -> adapter.adapt(tweet))
                    .collect(Collectors.toList());
            log.debug("Return {} tweets by filter {}", result.size(), filter);
            return result;
        } catch (final Exception e) {
            final String error = format("Unexpected error finding tweets by filter %s: %s", filter, e.getMessage());
            log.error(error);
            throw new InvalidTweetException(error);
        }
    }

    @Transactional
    @Override
    public void saveTweets(final List<Tweet> tweets) {
        try {
            tweets.forEach(tweet -> {
                final Tweet tweetByCode = getTweetByTweetId(tweet.getTweetId());
                if (tweetByCode != null) {
                    tweet.merge(tweetByCode, tweet);
                }
                final TweetEntity entity = adapter.adaptToRepo(tweet);
                repository.save(entity);
                log.debug("Tweet saved with tweetId : {}", entity.getTweetId().toString());

            });
        } catch (final Exception e) {
            log.error("Error creating tweets");
            throw new InvalidTweetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Tweet getTweetByCode(final Long code) {
        final Optional<TweetEntity> entity = repository.findById(code);
        if (!entity.isPresent()) {
            final String error = format("Tweet not found by code %d", code);
            log.error(error);
            return null;
        }
        return entity.map(tweet -> adapter.adapt(tweet)).get();
    }

    @Transactional
    @Override
    public Tweet getTweetByTweetId(final Long tweetId) {
        final Optional<TweetEntity> entity = repository.findByTweetId(tweetId
        );
        if (!entity.isPresent()) {
            final String error = format("Tweet not found by tweetId %d", tweetId);
            log.error(error);
            return null;
        }
        return entity.map(tweet -> adapter.adapt(tweet)).get();
    }

    @Transactional
    @Override
    public void validate(final Tweet tweet) {
        final TweetEntity entity = adapter.adaptToRepo(tweet);
        repository.save(entity);
    }
}
