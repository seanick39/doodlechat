package com.doodle.backend.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.UUID;

public class MessageNotFoundException extends BackendChallengeException {
    private static final String errorMessage = "Message not found with id {0}.";
    private static final String reason = "Not found.";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public MessageNotFoundException(final UUID messageId) {
        super(MessageFormat.format(errorMessage, messageId.toString()), reason, httpStatus);
    }
}
