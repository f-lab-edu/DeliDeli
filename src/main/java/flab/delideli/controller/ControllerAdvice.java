package flab.delideli.controller;

import flab.delideli.util.error.ErrorResponse;
import flab.delideli.util.error.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalStateException e) {

        return new ResponseEntity<>(ErrorResponse.of(StatusCode.CONFLICT_JOIN), HttpStatus.CONFLICT);

    }

}
