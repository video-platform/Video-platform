package com.video.demo.security.filter;

import com.video.demo.security.HeaderTokenExtractor;
import com.video.demo.security.handlers.JwtAuthenticationFailureHandler;
import com.video.demo.security.tokens.JwtPreProcessingToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;
    private HeaderTokenExtractor extractor;

    protected JwtAuthenticationFilter(RequestMatcher matcher) {
        super(matcher);
    }

    public JwtAuthenticationFilter(RequestMatcher matcher, JwtAuthenticationFailureHandler handler, HeaderTokenExtractor extractor){
        super(matcher);
        this.jwtAuthenticationFailureHandler = handler;
        this.extractor = extractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("Authorization");
        JwtPreProcessingToken token = new JwtPreProcessingToken(this.extractor.extract(tokenPayload));
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 인증에 성공하지 못해서 SecurityContext 를 지워줘야 한다
        SecurityContextHolder.clearContext();

        /*
             TODO : 인증 실패시 처리
             Login Filter 와 JWT filter는 엄연히 다른 것이다.
             Login Filter는 로그인에 대해서 검증하고 JWT filter는 로그인 후 리소스에 접근할 때 토큰을 검증하는 것이다.
             헷갈릴 까봐 TODO에 써놨다. 리팩토링 가능한 부분들은 리팩토링 하자.
             + 전체 객체에 대해 정리 한번 해야 할듯 그래야 어떤건지 알고 지울 수 있는 것들은 지우고
             추가 할 수 있는 것들은 추가 가능
         */
    }
}
