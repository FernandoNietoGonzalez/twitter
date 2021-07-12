package com.twitter.domain.port.input;

import com.twitter.domain.model.Hashtag;
import com.twitter.domain.model.filter.HashtagFilter;

import java.util.List;

public interface HashtagService {

    List<Hashtag> getHashtagsByFilter(HashtagFilter filter);

}
