package com.video.demo.security;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JwtFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);

    private MemberContext memberContext;

    @Autowired
    private JwtFactory jwtFactory;

    @BeforeEach
    void setup(){
        Member member = new Member();
        member.setMemberEmail("test@test.com");
        member.setMemberPw("1234");
        member.setUserRole(UserRole.USER);
        this.memberContext = MemberContext.fromMemberModel(member);
    }

    @Test
    void test_jwt_generate(){
        log.error(jwtFactory.generateToken(this.memberContext));
    }

}