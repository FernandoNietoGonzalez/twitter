package com.twitter.domain.exception;

public class TweetNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3764784539586853474L;

    public TweetNotFoundException() {
        super("Not Found!");
    }

    public TweetNotFoundException(final String error) {
        super(error);
    }
}