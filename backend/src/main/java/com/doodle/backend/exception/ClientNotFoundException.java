package com.doodle.backend.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.UUID;

public class ClientNotFoundException extends BackendChallengeException {

    private static final String errorMessage = "Client not found with {0} {1}.";
    private static final String reason = "Not found.";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public ClientNotFoundException(final UUID userId) {
        super(MessageFormat.format(errorMessage, "id",  userId.toString()), reason, httpStatus);
    }
    public ClientNotFoundException(final String userName) {
        super(MessageFormat.format(errorMessage, "name", userName), reason, httpStatus);
    }

}
