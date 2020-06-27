package com.video.demo.controller;

import com.video.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/upload")
    public String videoUploadPage(){
        return "";
    }

    @PostMapping("/upload")
    public String videoUpload(){
        return "";
    }
}
