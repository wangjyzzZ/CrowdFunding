package com.wood.crowd.exception;

public class AccessForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
