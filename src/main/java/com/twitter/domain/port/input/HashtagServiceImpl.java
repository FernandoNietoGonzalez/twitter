package com.twitter.domain.port.input;

import com.twitter.domain.model.Hashtag;
import com.twitter.domain.model.filter.HashtagFilter;
import com.twitter.domain.port.output.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository repository;

    @Override
    public List<Hashtag> getHashtagsByFilter(final HashtagFilter filter) {
        return repository.getHashtagsByFilter(filter);
    }
}
