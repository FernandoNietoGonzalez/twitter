package com.fernando.nieto.acciona.domain.port.input;

import com.fernando.nieto.acciona.domain.model.Hashtag;
import com.fernando.nieto.acciona.domain.model.filter.HashtagFilter;
import com.fernando.nieto.acciona.domain.port.output.HashtagRepository;
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
