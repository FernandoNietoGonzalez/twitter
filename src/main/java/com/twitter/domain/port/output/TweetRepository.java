package com.twitter.domain.port.output;

import com.twitter.domain.model.Tweet;
import com.twitter.domain.model.filter.TweetFilter;

import java.util.List;

public interface TweetRepository {

    List<Tweet> getTweetsByFilter(TweetFilter filter);

    void saveTweets(List<Tweet> tweets);

    Tweet getTweetByCode(Long code);

    Tweet getTweetByTweetId(Long tweetId);

    void validate(Tweet tweet);

}
