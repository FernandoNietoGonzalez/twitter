package com.fernando.nieto.acciona.domain.port.output;

import com.fernando.nieto.acciona.domain.model.Hashtag;
import com.fernando.nieto.acciona.domain.model.filter.HashtagFilter;

import java.util.List;

public interface HashtagRepository {

    List<Hashtag> getHashtagsByFilter(HashtagFilter filter);

    void saveHashtags(final List<Hashtag> hashtags);

    Hashtag getHashtagByText(final String text);
}
