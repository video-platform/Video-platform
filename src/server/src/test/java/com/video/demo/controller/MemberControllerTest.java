package com.video.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@WebAppConfiguration
class MemberControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() throws  Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    void memberSignInTest() throws Exception {
        Member member = new Member();
        member.setUserRole(UserRole.USER);
        member.setMemberEmail("test@test.com");
        member.setMemberPw("1234");
        member.setMemberName("jongmin");
        MockHttpServletResponse response = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(member))).andReturn().getResponse();
        log.info("response : {}", response.getContentAsString());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}