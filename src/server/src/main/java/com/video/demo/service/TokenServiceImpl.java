package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.Token;
import com.video.demo.exception.DataIntegrityViolationException;
import com.video.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token getAccessToken(String token) {

        Token tokens = tokenRepository.findByAccessToken(token);
        return tokens;
    }

    @Override
    public void deleteAccessToken(String accessToken) {
        tokenRepository.deleteByAccessToken(accessToken);
    }

    @Override
    public void insertToken(Token token) {
        try{
            tokenRepository.save(token);
        }catch (DataIntegrityViolationException | JpaSystemException ex){
            throw new DataIntegrityViolationException("로그인에 실패했습니다.");
        }
    }
}
