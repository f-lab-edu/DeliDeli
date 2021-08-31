package flab.delideli.controller;

import flab.delideli.exception.DuplicatedIdException;
import flab.delideli.exception.WrongLoginInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<Void> DuplicatedIdExceptionAdvice() {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongLoginInfoException.class)
    public ResponseEntity<Void> WrongLoginInfoExceptionAdvice() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
