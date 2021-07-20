package flab.delideli.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final ResponseEntity responseConflict =
            new ResponseEntity(HttpStatus.CONFLICT);
    private final ResponseEntity responseBadRequest =
            new ResponseEntity(HttpStatus.BAD_REQUEST);

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity illegalStateException(IllegalStateException e) {

        return responseConflict;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        return responseBadRequest;

    }

}
