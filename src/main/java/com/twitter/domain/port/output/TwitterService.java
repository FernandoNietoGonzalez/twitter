package com.twitter.domain.port.output;

import com.twitter.domain.model.Tweet;

import java.util.List;

public interface TwitterService {
    List<Tweet> getTweetsToPersist(Integer followers, List<String> languages);
}
