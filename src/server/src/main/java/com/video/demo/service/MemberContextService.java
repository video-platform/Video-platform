package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.repository.MemberRepository;
import com.video.demo.security.MemberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

// TODO : MemberContextService 를 사용하지 않을 수도 있다. LoginAuthenticationProvider 에서 처리할 수도 있음
@Service
public class MemberContextService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member =  memberRepository.findByMemberEmail(username)
                .orElseThrow(()->new NoSuchElementException("아이디에 맞는 계정이 없습니다."));

        return getMemberContext(member);
    }

    private MemberContext getMemberContext(Member member){
        return MemberContext.fromMemberModel(member);
    }
}
