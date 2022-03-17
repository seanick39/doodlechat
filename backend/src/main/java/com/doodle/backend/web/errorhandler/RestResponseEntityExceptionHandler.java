package com.doodle.backend.web.errorhandler;

import com.doodle.backend.exception.BackendChallengeException;
import com.doodle.backend.exception.error.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(BackendChallengeException.class)
    public ResponseEntity<ErrorDetails> backendServiceException(final BackendChallengeException exception){
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), exception.getHttpStatus(), exception.getReason(), exception.getLocalizedMessage());
        logger.error(exception.getReason(), exception.getMessage());
        return new ResponseEntity<>(errorDetails, exception.getHttpStatus());
    }

}
