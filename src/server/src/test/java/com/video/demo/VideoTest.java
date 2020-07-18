package com.video.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Comments;
import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import com.video.demo.domain.Video;
import com.video.demo.service.VideoService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;



import java.io.IOException;
import java.sql.Timestamp;



@SpringBootTest
public class VideoTest {

    @Autowired
    private VideoService videoService;


    @Test
    public void videoUploadTest() throws IOException {
        MockMultipartFile multipartFile  = new MockMultipartFile("file","test.txt" , "text/plain" , "hello file".getBytes());

        videoService.videoUpload(multipartFile);
    }


    @Test
    public void objectToJson() throws JsonProcessingException {
        Member member = new Member(1L,"test@test.com","test","test", UserRole.USER);
        Video video = new Video("videoName");
        Comments comments = new Comments(1L, video, member,"가나다가라마사",new Timestamp(System.currentTimeMillis()));
        ObjectMapper objectMapper = new ObjectMapper();
        String commentsAsString = objectMapper.writeValueAsString(comments);

        System.out.println(commentsAsString);
    }
}
