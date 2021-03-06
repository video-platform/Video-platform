package com.video.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class ResponseMessage {

    private int status;
    private Object data;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public ResponseMessage(Object data, String message) {
        this.status = HttpStatus.OK.value();
        this.time = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }

}
