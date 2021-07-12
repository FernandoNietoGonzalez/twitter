package com.fernando.nieto.acciona.domain.port.input;

import com.fernando.nieto.acciona.domain.model.Hashtag;
import com.fernando.nieto.acciona.domain.model.filter.HashtagFilter;

import java.util.List;

public interface HashtagService {

    List<Hashtag> getHashtagsByFilter(HashtagFilter filter);

}
