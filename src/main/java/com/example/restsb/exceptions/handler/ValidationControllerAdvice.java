package com.example.restsb.exceptions.handler;

import com.example.restsb.exceptions.ValidationErrorResponse;
import com.example.restsb.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleException(ValidationException e){
        return new ResponseEntity<>(
                ValidationErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .timestamp(System.currentTimeMillis())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleException(InternalServerError e){
        return new ResponseEntity<>(
                ValidationErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .timestamp(System.currentTimeMillis())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
