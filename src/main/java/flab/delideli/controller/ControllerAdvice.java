package flab.delideli.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private final ResponseEntity<Void> responseConflict =
            new ResponseEntity<>(HttpStatus.CONFLICT);
    private final ResponseEntity<Void> responseBadRequest =
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Void> illegalStateException(IllegalStateException e) {

        return responseConflict;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> methodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        return responseBadRequest;

    }

}
