package com.video.demo.security.providers;

import com.video.demo.domain.Member;
import com.video.demo.repository.MemberRepository;
import com.video.demo.security.MemberContext;
import com.video.demo.security.tokens.PostAuthorizationToken;
import com.video.demo.security.tokens.PreAuthorizationToken;
import com.video.demo.service.MemberContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberContextService memberContextService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 인증 객체 생성
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;
        String username = token.getUsername();
        String password = token.getPassword();

        Member member = memberRepository.findByMemberEmail(username)
                .orElseThrow(() -> new NoSuchElementException("정보에 맞는 계정이 없습니다."));

        if(isCorrectPassword(password, member)){
            return PostAuthorizationToken.getTokenMemberContext(MemberContext.fromMemberModel(member));
        }

        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    // PreAuthorizationToken에 요청하면 이 Filter에 걸리게된다.
    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    // matches : 원본이 앞에 와야 한다.
    private boolean isCorrectPassword(String password, Member member){
        return passwordEncoder.matches(member.getMemberPw(), password);
    }

}