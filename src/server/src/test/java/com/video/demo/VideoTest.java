package com.video.demo;

import com.video.demo.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


import java.io.IOException;

@SpringBootTest
public class VideoTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void videoUploadTest() throws IOException {
        MockMultipartFile multipartFile  = new MockMultipartFile("file","test.txt" , "text/plain" , "hello file".getBytes());

        videoService.videoUpload(multipartFile);
    }
}
