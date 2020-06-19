package com.video.demo.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(name = "MEMBER_EMAIL", unique = true, length = 80)
    private String memberEmail;

    @Column(name = "MEMBER_PW", length = 80)
    private String memberPw;

    @Column(name = "MEMBER_NAME", length = 60)
    private String MemberName;

    @Column(name = "MEMBER_ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

}
