package com.mobilidade.web.exception;

public class ApplicationException extends RuntimeException {

    private int status;

    public ApplicationException(final int status, final String message) {
        this(message, null);
        this.status = status;
    }

    private ApplicationException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }

    public Integer getStatus() {
        return status;
    }
}
