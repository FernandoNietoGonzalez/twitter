package com.fernando.nieto.acciona.domain.port.output;

import com.fernando.nieto.acciona.domain.model.Tweet;

import java.util.List;

public interface TwitterService {
    List<Tweet> getTweetsToPersist(Integer followers, List<String> languages);
}
