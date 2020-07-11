package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setUp(){
        member = new Member();
        member.setMemberEmail("test@test.com");
        member.setMemberPw("test");
        member.setMemberName("jongmin");
        member.setUserRole(UserRole.USER);
    }

    @Test
    void memberSignUpTest(){
        memberService.memberSignUp(member);
    }

}