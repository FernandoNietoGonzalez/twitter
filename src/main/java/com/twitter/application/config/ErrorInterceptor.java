package com.twitter.application.config;

import com.twitter.domain.exception.InvalidDataException;
import com.twitter.domain.exception.InvalidTweetException;
import com.twitter.domain.exception.TweetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorInterceptor {

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity handleInvalid(final InvalidDataException ex) {
        log.error("Caught exception: {}", ex.getMessage());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TweetNotFoundException.class})
    public ResponseEntity handleNotFound(final TweetNotFoundException ex) {
        log.error("Caught exception: {}", ex.getMessage());
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidTweetException.class})
    public ResponseEntity handle(final Exception ex) {
        log.error("Caught exception: {}", ex);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}