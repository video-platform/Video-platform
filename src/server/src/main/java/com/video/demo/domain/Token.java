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

    @OneToOne
    @JoinColumn(name = "member_no")
    private Member member;

    Token(){}

    public Token(String accessToken, String refreshToken, String email, String username, UserRole userRole, long memberNo){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

        Member tempMember = new Member();
        tempMember.setMemberEmail(email);
        tempMember.setMemberName(username);
        tempMember.setUserRole(userRole);
        tempMember.setMemberPw("");
        tempMember.setMemberNo(memberNo);
        this.member = tempMember;
    }

    
}
