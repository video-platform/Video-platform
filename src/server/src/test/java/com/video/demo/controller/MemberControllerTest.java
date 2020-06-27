package com.video.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Member;
import com.video.demo.domain.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@WebAppConfiguration
class MemberControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() throws  Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity()).build();
    }


    @Test
    @Disabled
    void memberSignInTest() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setMemberEmail("test@test.com");
        loginDto.setMemberPw("test");
        MockHttpServletResponse response = mockMvc.perform(post("/signin").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginDto))).andDo(print()).andReturn().getResponse();
        log.info("response : {}", response.getContentAsString());
    }

    @Test
    void memberSignUpTest() throws Exception {
        Member member = new Member();
        member.setMemberName("jongmin");
        member.setMemberEmail("test@test.com");
        MockHttpServletResponse response = mockMvc.perform(post("/member/signup").contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(member))).andDo(print()).andReturn().getResponse();
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