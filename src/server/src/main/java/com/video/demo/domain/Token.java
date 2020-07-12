package com.video.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "\"token\"")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_NO")
    private Long tokenNo;

    @Column(name = "ACCESS_TOKEN", unique = true, length = 200)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", unique = true, length = 200)
    private String refreshToken;

    @OneToOne(mappedBy = "token")
    private Member member;

    Token(){}

    public Token(String accessToken, String refreshToken, String member){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

        Member tempMember = new Member();
        tempMember.setMemberEmail(member);
        this.member = tempMember;
    }

    
}
