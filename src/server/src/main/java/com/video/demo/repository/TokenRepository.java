package com.video.demo.repository;

import com.video.demo.domain.Member;
import com.video.demo.domain.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Token findByAccessToken(String accessToken);

    public void deleteByAccessToken(String accessToken);

}