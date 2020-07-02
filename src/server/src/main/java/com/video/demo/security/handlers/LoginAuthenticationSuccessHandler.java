package com.video.demo.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.security.JwtFactory;
import com.video.demo.security.MemberContext;
import com.video.demo.security.tokens.PostAuthorizationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "LoginAuthenticationSuccessHandler")
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtFactory jwtFactory;

    private final ObjectMapper objectMapper;

    public LoginAuthenticationSuccessHandler(JwtFactory jwtFactory, ObjectMapper objectMapper) {
        this.jwtFactory = jwtFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        PostAuthorizationToken postAuthorizationToken = (PostAuthorizationToken) auth;

        MemberContext context = (MemberContext) postAuthorizationToken.getPrincipal();
        String tokenString = jwtFactory.generateToken(context);
        processResponse(response, tokenString);

    }


    private void processResponse(HttpServletResponse res, String token) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());

        ResponseMessage responseMessage = new ResponseMessage("Bearer " + token, "로그인에 성공했습니다.");
        res.getWriter().write(objectMapper.writeValueAsString(responseMessage));
    }

}
