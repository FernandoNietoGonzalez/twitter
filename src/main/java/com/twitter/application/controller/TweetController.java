package com.twitter.application.controller;

import com.twitter.api.TweetsApi;
import com.twitter.application.controller.adapter.TweetControllerAdapter;
import com.twitter.domain.model.Tweet;
import com.twitter.domain.model.filter.TweetFilter;
import com.twitter.domain.port.input.TweetService;
import com.twitter.model.TweetApiDto;
import com.twitter.model.UpdateTweetApiDto;
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
