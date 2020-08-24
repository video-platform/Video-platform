package com.video.demo.exception;

public class InvalidJwtException extends RuntimeException {

    InvalidJwtException(){}

    public InvalidJwtException(String message){
        super(message);
    }

}
