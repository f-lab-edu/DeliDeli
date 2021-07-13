package flab.delideli.controller;

import flab.delideli.util.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    private ResponseEntity<ErrorResponse> responseEntity;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateException(
            IllegalStateException e) {

        ErrorResponse response = new ErrorResponse(
                "가입 실패: " + e.getMessage());
        responseEntity = new ResponseEntity<>(response, HttpStatus.CONFLICT);

        return responseEntity;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        ErrorResponse response = new ErrorResponse("필수값 누락: " +
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return responseEntity;

    }

}
