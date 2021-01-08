package com.app.notebook.exception;

public class UnexpectedUserSpaceException extends RuntimeException {

    public UnexpectedUserSpaceException() {
    }

    public UnexpectedUserSpaceException(String message) {
        super(message);
    }
}
