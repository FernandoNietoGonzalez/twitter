package com.twitter.domain.port.input;

import com.twitter.domain.exception.TweetNotFoundException;
import com.twitter.domain.model.Hashtag;
import com.twitter.domain.model.Tweet;
import com.twitter.domain.model.filter.TweetFilter;
import com.twitter.domain.port.output.HashtagRepository;
import com.twitter.domain.port.output.TweetRepository;
import com.twitter.domain.port.output.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@Slf4j
class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private TwitterService twitterService;

    @Value("${acciona.twitter.followers}")
    private Integer followers;

    @Value("${acciona.twitter.languages}")
    private List<String> languages;

    @Override
    public List<Tweet> getTweetsByFilter(final TweetFilter filter) {
        return tweetRepository.getTweetsByFilter(filter);
    }

    @Override
    public void saveTweets() {
        log.info("Finding tweets to persist.");
        final List<Tweet> tweetsToPersist = twitterService.getTweetsToPersist(followers, languages);
        if (tweetsToPersist != null && !tweetsToPersist.isEmpty()) {
            log.info("Found tweets to persist.");
            tweetRepository.saveTweets(tweetsToPersist);
            log.info("Saved tweets.");
            final List<Hashtag> hashtags = tweetsToPersist.stream()
                    .flatMap(tweet -> tweet.getHashtags().stream())
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
            log.info("Saving hashtags.");
            hashtagRepository.saveHashtags(hashtags);

        }
    }

    @Override
    public void validateTweet(final Tweet tweet) {
        final Tweet tweetByCode = tweetRepository.getTweetByCode(tweet.getId());
        if (tweetByCode == null) {
            final String error = format("Tweet not found by code : %d", tweet.getId());
            log.error(error);
            throw new TweetNotFoundException(error);
        }
        tweetByCode.setValidated(tweet.isValidated());
        tweetRepository.validate(tweetByCode);

    }
}
