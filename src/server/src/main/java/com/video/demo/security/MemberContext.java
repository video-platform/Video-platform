package com.video.demo.security;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberContext extends User {

    private MemberContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static MemberContext fromMemberModel(Member member){
        return new MemberContext(member.getMemberEmail(), member.getMemberPw(), parseAuthorities(member.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role){
        return Stream.of(role).map(r-> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

}
