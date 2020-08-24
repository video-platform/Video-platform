package com.video.demo.repository;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(MemberRepositoryTest.class);

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Order(value = 2)
    void findByMemberEmailTest(){
        String memberEmail = "test@test.com";
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);
        logger.info("Member name : {}", member.get().getMemberName());
    }

    @Test
    @Order(value = 1)
    void memberInsertTest(){
        Member member = new Member();
        member.setMemberEmail("test@test.com");
        member.setMemberName("jongminLee");
        member.setMemberPw("test");
        member.setUserRole(UserRole.USER);
        memberRepository.save(member);
    }


}