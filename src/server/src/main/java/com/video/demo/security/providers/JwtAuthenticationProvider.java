package com.video.demo.security.providers;


import com.video.demo.security.JwtDecoder;
import com.video.demo.security.MemberContext;
import com.video.demo.security.tokens.JwtPreProcessingToken;
import com.video.demo.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getPrincipal();
        MemberContext memberContext = jwtDecoder.decodeJwt(token); // jwt 유효성 검증

        return PostAuthorizationToken.getTokenMemberContext(memberContext);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
