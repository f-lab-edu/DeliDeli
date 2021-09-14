package flab.delideli.controller;

import static flab.delideli.util.ResponseEntityCode.CONFLICT_RESPONSE_ENTITY;
import static flab.delideli.util.ResponseEntityCode.UNAUTHORIZED_RESPONSE_ENTITY;

import flab.delideli.exception.DuplicatedIdException;
import flab.delideli.exception.WrongLoginInfoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<Void> DuplicatedIdExceptionAdvice() {
        return CONFLICT_RESPONSE_ENTITY;
    }

    @ExceptionHandler(WrongLoginInfoException.class)
    public ResponseEntity<Void> WrongLoginInfoExceptionAdvice() {
        return UNAUTHORIZED_RESPONSE_ENTITY;
    }

}
