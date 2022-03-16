package com.doodle.backend.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class ClientNotFoundException extends BackendChallengeException {

    private static final String errorMessage = "Client not found with id {0}.";
    private static final String reason = "Not found.";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public ClientNotFoundException(final String userId) {
        super(MessageFormat.format(errorMessage, userId), reason, httpStatus);
    }

}
