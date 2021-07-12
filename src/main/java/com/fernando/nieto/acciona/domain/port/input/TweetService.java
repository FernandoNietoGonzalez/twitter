package com.fernando.nieto.acciona.domain.port.input;

import com.fernando.nieto.acciona.domain.model.Tweet;
import com.fernando.nieto.acciona.domain.model.filter.TweetFilter;

import java.util.List;

public interface TweetService {

    List<Tweet> getTweetsByFilter(TweetFilter filter);
    void saveTweets();
    void validateTweet(Tweet tweet);

}
