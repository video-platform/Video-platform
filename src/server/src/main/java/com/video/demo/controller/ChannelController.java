package com.video.demo.controller;

import com.video.demo.domain.dto.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("channel")
public class ChannelController {

    @PostMapping("createChannel")
    public ResponseEntity<ResponseMessage> createChannel(){

        return null;
    }

    @PostMapping("createPlayList")
    public ResponseEntity<ResponseMessage> createPlayList(){

        return null;
    }

    public ResponseEntity<ResponseMessage> addPlayList(){

        return null;
    }
}
