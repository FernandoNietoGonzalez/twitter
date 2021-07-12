package com.twitter.application.controller;

import com.twitter.api.HashtagApi;
import com.twitter.application.controller.adapter.HashtagControllerAdapter;
import com.twitter.domain.model.filter.HashtagFilter;
import com.twitter.domain.model.filter.OrderEnum;
import com.twitter.domain.port.input.HashtagService;
import com.twitter.model.HashtagApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HashtagController implements HashtagApi {

    @Autowired
    private HashtagService service;

    private HashtagControllerAdapter adapter = HashtagControllerAdapter.INSTANCE;

    @Override
    public ResponseEntity<List<HashtagApiDto>> hashtagGet(
            final Integer pageSize,
            final Integer pageNumber,
            final String orderBy
    ) {
        final HashtagFilter filter = new HashtagFilter(
                pageSize,
                pageNumber,
                orderBy != null ? OrderEnum.valueOf(orderBy.toUpperCase()) : null);
        final List<HashtagApiDto> result = service.getHashtagsByFilter(filter)
                .stream()
                .map(tweet -> adapter.adapt(tweet))
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
