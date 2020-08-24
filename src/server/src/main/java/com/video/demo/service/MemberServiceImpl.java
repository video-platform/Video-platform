package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ResponseMessage memberSignUp(Member member) {
        try{
            String tempPwd = member.getMemberPw();
            member.setMemberPw(new BCryptPasswordEncoder().encode(tempPwd));
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("비밀번호를 입력해 주세요.");
        }
        member.setUserRole(UserRole.USER); // Default Role을 User로 설정한다.

        // 중복 처리
        Optional<Member> getMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        getMember.ifPresent(ex ->{
            throw new DuplicateKeyException("중복되는 이메일을 사용할 수 없습니다.");
        });

        try{
            memberRepository.save(member);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("회원가입에 실패 했습니다.");
        }
        return new ResponseMessage(null, "회원가입에 성공했습니다.");
    }

}
