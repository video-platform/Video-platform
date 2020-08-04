package com.video.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.video.demo.domain.*;
import com.video.demo.repository.ChannelRepository;
import com.video.demo.repository.MemberRepository;
import com.video.demo.service.VideoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;



import java.io.IOException;
import java.sql.Timestamp;


@SpringBootTest
public class VideoTest {

    private static final Logger logger = LoggerFactory.getLogger(VideoTest.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChannelRepository channelRepository;


    public void videoUploadTest() throws IOException {
        MockMultipartFile multipartFile  = new MockMultipartFile("file","test.txt" , "text/plain" , "hello file".getBytes());

//        videoService.videoUpload(multipartFile,);
    }


    public void jpaModelCreate(){
        Member member = new Member();
        Channel channel = new Channel();
        Video video = new Video();
    }
    @Test
    @Order(value = 2)
    public void jpaTest(){
        Channel channel = new Channel();
        channel.setChannelNo(1L);
        channel.setChannelName("TEST");
        Member member = new Member();
        member.setMemberNo(1L);
        channel.setMember(member);
        channelRepository.save(channel);
    }

    @Test
    public void jpaSelectTest(){
        Channel channel = channelRepository.findById(1L).get();
        Member member = channel.getMember();
        logger.info("channel : {}",channel);
        logger.info("memberName : {}",member.getMemberName());
    }


    public void jpaDeleteTest(){
        Member member = new Member();
        member.setMemberName("test");
        member.setMemberEmail("testtest@test.com");

    }

    public void objectToJson() throws JsonProcessingException {
        Member member = new Member(1L,"test@test.com","test","test", UserRole.USER);
//        Video video = new Video();
//        Comments comments = new Comments(1L, video, member,"가나다가라마사",new Timestamp(System.currentTimeMillis()));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String commentsAsString = objectMapper.writeValueAsString(comments);

//        System.out.println(commentsAsString);
    }
}
