package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseMessage memberSignUp(Member member) {
        // TODO : 회원가입 처리
        String tempPwd = member.getMemberPw();
        member.setMemberPw(new BCryptPasswordEncoder().encode(tempPwd));
        memberRepository.save(member);

        return null;
    }
}
