package com.video.demo.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.demo.domain.Token;
import com.video.demo.domain.dto.ResponseMessage;
import com.video.demo.domain.dto.TokenDTO;
import com.video.demo.repository.MemberRepository;
import com.video.demo.security.JwtFactory;
import com.video.demo.security.MemberContext;
import com.video.demo.security.tokens.PostAuthorizationToken;
import com.video.demo.service.MemberService;
import com.video.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenService tokenService;

    public LoginAuthenticationSuccessHandler(JwtFactory jwtFactory, ObjectMapper objectMapper) {
        this.jwtFactory = jwtFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        PostAuthorizationToken postAuthorizationToken = (PostAuthorizationToken) auth;

        MemberContext context = (MemberContext) postAuthorizationToken.getPrincipal();
        String tokenString = jwtFactory.generateToken(context);
        String refreshToken = jwtFactory.generateRefreshToken(tokenString);

        long memberNo = memberRepository.findByMemberEmail(context.getUsername()).get().getMemberNo();
        Token token = new Token(tokenString, refreshToken, context.getUsername(), context.getMember().getMemberName(), context.getMember().getUserRole(), memberNo);
        processResponse(response, token);

    }


    private void processResponse(HttpServletResponse res, Token token) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());

        Token token1 = tokenService.getAccessToken(token.getAccessToken());
        if(token1 == null)
            tokenService.insertToken(token); // access_token, refresh_token db삽입

        TokenDTO tokenDTO = new TokenDTO(token.getAccessToken(), token.getRefreshToken());
        ResponseMessage responseMessage = new ResponseMessage(tokenDTO, "로그인에 성공했습니다.");
        res.getWriter().write(objectMapper.writeValueAsString(responseMessage));
    }

}
