package flab.delideli.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    private ResponseEntity<Void> responseEntity;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> illegalArgumentExceptionAdvice(IllegalArgumentException e) {

        if(e.getMessage().equals("이미 존재하는 아이디입니다.")) {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if(e.getMessage().equals("아이디 혹은 비밀번호가 일치하지 않습니다.")) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;

    }

}
