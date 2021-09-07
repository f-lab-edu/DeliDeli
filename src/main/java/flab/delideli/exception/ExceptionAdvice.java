package flab.delideli.exception;

import flab.delideli.exception.AlreadyAddedShopException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyAddedShopException.class)
    public ResponseEntity<Void> AlreadyAddedShopExceptionAdvice() {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity unauthorizedExceptionAdvice(UnauthorizedException e) {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
