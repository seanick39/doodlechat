package com.doodle.backend.exception;

import org.springframework.http.HttpStatus;

public class BackendChallengeException extends RuntimeException {

    private String reason;
    private HttpStatus httpStatus;

    public BackendChallengeException(final String errorMessage, final String reason, final HttpStatus httpStatus) {
        super(errorMessage);
        this.reason = reason;
        this.httpStatus = httpStatus;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
