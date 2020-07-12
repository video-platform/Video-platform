package com.video.demo.service;

import com.video.demo.domain.Member;
import com.video.demo.domain.Token;
import com.video.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token getAccessToken(String memberEmail) {
        Member member = new Member();
        member.setMemberEmail(memberEmail);

        Token token = tokenRepository.findByMember(member);
        return token;
    }

    @Override
    public void deleteAccessToken(String accessToken) {
        tokenRepository.deleteByAccessToken(accessToken);
    }

    @Override
    public void insertToken(Token token) {
        tokenRepository.save(token);
    }
}
