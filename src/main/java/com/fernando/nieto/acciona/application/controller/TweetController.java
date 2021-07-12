package com.fernando.nieto.acciona.application.controller;

import com.fernando.nieto.acciona.application.controller.adapter.TweetControllerAdapter;
import com.fernando.nieto.acciona.domain.model.Tweet;
import com.fernando.nieto.acciona.domain.model.filter.TweetFilter;
import com.fernando.nieto.acciona.domain.port.input.TweetService;
import com.fernando.nieto.accionaapi.api.TweetsApi;
import com.fernando.nieto.accionaapi.model.TweetApiDto;
import com.fernando.nieto.accionaapi.model.UpdateTweetApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TweetController implements TweetsApi {

    @Autowired
    private TweetService service;

    private TweetControllerAdapter adapter = TweetControllerAdapter.INSTANCE;

    @Override
    public ResponseEntity<List<TweetApiDto>> tweetsGet(
            final Integer pageSize,
            final Integer pageNumber,
            final String userName,
            final Boolean validated
    ) {
        final TweetFilter filter = new TweetFilter(pageSize, pageNumber, userName, validated);
        final List<TweetApiDto> result = service.getTweetsByFilter(filter)
                .stream()
                .map(tweet -> adapter.adapt(tweet))
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> tweet(final Long tweetId, final UpdateTweetApiDto updateTweetApiDto) {
        final Tweet tweet = adapter.adapt(updateTweetApiDto, tweetId);
        service.validateTweet(tweet);
        return new ResponseEntity(HttpStatus.OK);
    }


}
