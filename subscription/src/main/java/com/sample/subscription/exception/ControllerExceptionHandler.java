package com.sample.subscription.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> validationException(final ValidationException ex) {
        final ErrorMessage message = getErrorMessage(HttpStatus.NOT_ACCEPTABLE, ex);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorMessage> executionException(final ExecutionException ex) {
        if (ex.getCause() instanceof ResourceAccessException) {
            final ErrorMessage message = getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Server is down! Try again after sometime");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return globalExceptionHandler(ex);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorMessage> timeOutException(final TimeoutException ex) {
        final ErrorMessage message = getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Could not complete validation! Try again");
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedProductException.class)
    public ResponseEntity<ErrorMessage> unsupportedProductException(final UnsupportedProductException ex) {
        final ErrorMessage message = getErrorMessage(HttpStatus.NOT_ACCEPTABLE, ex);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentException(final IllegalArgumentException ex) {
        final ErrorMessage message = getErrorMessage(HttpStatus.NOT_ACCEPTABLE, ex);
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageNotReadableException() {
        final ErrorMessage message = getErrorMessage(HttpStatus.NOT_ACCEPTABLE, "Invalid input");
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(final Exception ex) {
        final ErrorMessage message = getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorMessage getErrorMessage(final HttpStatus httpStatus, final Exception ex) {
        return getErrorMessage(httpStatus, ex.getMessage());
    }

    private ErrorMessage getErrorMessage(final HttpStatus httpStatus, final String message) {
        return new ErrorMessage(
                httpStatus.value(),
                new Date(),
                message);
    }
}