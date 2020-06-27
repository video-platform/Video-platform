package com.video.demo.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorMessage {

    private String message;
    private int errorCode;
    private String detail;
    private LocalDateTime time;

}
