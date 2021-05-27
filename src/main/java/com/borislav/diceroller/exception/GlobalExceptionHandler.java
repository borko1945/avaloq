package com.borislav.diceroller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorMessage exceptionHandler(ConstraintViolationException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    public static class ErrorMessage {
        private HttpStatus code;
        private String message;

        public ErrorMessage(HttpStatus code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
