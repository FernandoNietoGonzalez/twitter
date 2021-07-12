package com.fernando.nieto.acciona.domain.exception;

public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = -2386452178326638319L;

    public InvalidDataException() {
        super("Invalid Data!");
    }

    public InvalidDataException(final String s) {
        super(s);
    }
}