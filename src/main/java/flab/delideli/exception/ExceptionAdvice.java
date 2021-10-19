package flab.delideli.exception;

import static flab.delideli.util.ResponseEntityCode.CONFLICT_RESPONSE_ENTITY;
import static flab.delideli.util.ResponseEntityCode.UNAUTHORIZED_RESPONSE_ENTITY;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AlreadyAddedValueException.class)
    public ResponseEntity<Void> AlreadyAddedValueExceptionAdvice() {
        return CONFLICT_RESPONSE_ENTITY;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Void> unauthorizedExceptionAdvice() {
        return UNAUTHORIZED_RESPONSE_ENTITY;
    }

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<Void> DuplicatedIdExceptionAdvice() {
        return CONFLICT_RESPONSE_ENTITY;
    }

    @ExceptionHandler(WrongLoginInfoException.class)
    public ResponseEntity<Void> WrongLoginInfoExceptionAdvice() {
        return UNAUTHORIZED_RESPONSE_ENTITY;
    }

}