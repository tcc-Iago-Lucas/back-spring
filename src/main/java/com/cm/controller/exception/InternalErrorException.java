package com.cm.controller.exception;

public class InternalErrorException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}

