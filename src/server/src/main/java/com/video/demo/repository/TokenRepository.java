package com.video.demo.repository;

import com.video.demo.domain.Member;
import com.video.demo.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Token findByMember(Member member);

    public void deleteByAccessToken(String accessToken);

}
