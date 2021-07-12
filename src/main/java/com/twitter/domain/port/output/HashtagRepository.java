package com.twitter.domain.port.output;

import com.twitter.domain.model.Hashtag;
import com.twitter.domain.model.filter.HashtagFilter;

import java.util.List;

public interface HashtagRepository {

    List<Hashtag> getHashtagsByFilter(HashtagFilter filter);

    void saveHashtags(final List<Hashtag> hashtags);

    Hashtag getHashtagByText(final String text);
}
