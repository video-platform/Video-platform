package com.video.demo.security.providers;

import com.video.demo.domain.Member;
import com.video.demo.repository.MemberRepository;
import com.video.demo.security.MemberContext;
import com.video.demo.security.tokens.PostAuthorizationToken;
import com.video.demo.security.tokens.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

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
        Member member = new Member();
        try{
            member = memberRepository.findByMemberEmail(username).get();
        }catch (NoSuchElementException ex){
            return null;
        }

        if(isCorrectPassword(password, member)){
            return PostAuthorizationToken.getTokenMemberContext(MemberContext.fromMemberModel(member));
        }

        // LoginFilter unsuccessfulAuthentication 에서 처리
        return null;
    }

    // PreAuthorizationToken에 요청하면 이 Filter에 걸리게된다.
    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    // matches : 원본이 앞에 와야 한다.
    private boolean isCorrectPassword(String password, Member member){
        return passwordEncoder.matches(password, member.getMemberPw());
    }

}
