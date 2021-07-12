package com.fernando.nieto.acciona.domain.exception;

public class InvalidTweetException extends RuntimeException {

    private static final long serialVersionUID = 708824858274648007L;

    public InvalidTweetException(final String error) {
        super(error);
    }
}