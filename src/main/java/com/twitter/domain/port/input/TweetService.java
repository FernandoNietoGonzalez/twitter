package com.twitter.domain.port.input;

import com.twitter.domain.model.Tweet;
import com.twitter.domain.model.filter.TweetFilter;

import java.util.List;

public interface TweetService {

    List<Tweet> getTweetsByFilter(TweetFilter filter);
    void saveTweets();
    void validateTweet(Tweet tweet);

}
