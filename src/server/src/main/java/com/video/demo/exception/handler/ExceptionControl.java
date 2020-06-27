package com.video.demo.exception.handler;

import com.video.demo.exception.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControl {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> DataIntegrityViolationException(DataIntegrityViolationException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> IllegalArgumentException(IllegalArgumentException ex) {
        return getErrorMessageResponseEntity(ex.getMessage(), ex.toString(), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorMessage> getErrorMessageResponseEntity(String message, String s, HttpStatus httpStatus) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTime(LocalDateTime.now());
        errorMessage.setMessage(message);
        errorMessage.setErrorCode(httpStatus.value());
        errorMessage.setDetail(s);

        return new ResponseEntity<>(errorMessage, httpStatus);
    }
}
