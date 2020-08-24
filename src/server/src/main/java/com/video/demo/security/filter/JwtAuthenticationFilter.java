package com.video.demo.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.exception.ErrorMessage;
import com.video.demo.exception.InvalidJwtException;
import com.video.demo.security.HeaderTokenExtractor;
import com.video.demo.security.handlers.JwtAuthenticationFailureHandler;
import com.video.demo.security.tokens.JwtPreProcessingToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
        JwtPreProcessingToken token = null;
        try{
            token = new JwtPreProcessingToken(this.extractor.extract(tokenPayload));
        }catch (InvalidJwtException ex){
            unSuccessfulAuthenticationMessage(response, ex.getMessage());
        }

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

        unSuccessfulAuthenticationMessage(response, failed.getMessage());

    }

    static void unSuccessfulAuthenticationMessage(HttpServletResponse response, String message) throws IOException {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTime();
        errorMessage.setMessage("인증정보가 정확하지 않습니다.");
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setDetail(message);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorMessage));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
