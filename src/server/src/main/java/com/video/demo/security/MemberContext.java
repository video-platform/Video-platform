package com.video.demo.security;

import com.video.demo.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private MemberContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static MemberContext fromMemberModel(Member member){
        return new MemberContext(member.getMemberEmail(), member.getMemberPw(), null);
    }

}
