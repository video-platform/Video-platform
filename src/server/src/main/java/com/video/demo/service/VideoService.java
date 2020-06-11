package com.video.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    String videoUpload(MultipartFile multipartFile) throws IOException;
    void videoIncoding(String videoId);
}
