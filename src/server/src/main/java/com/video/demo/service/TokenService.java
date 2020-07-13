package com.video.demo.service;

import com.video.demo.domain.Token;

public interface TokenService {

    public Token getAccessToken(String token);

    public void deleteAccessToken(String accessToken);

    public void insertToken(Token token);

}
