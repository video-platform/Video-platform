package com.video.demo.security;

import com.video.demo.domain.Member;
import com.video.demo.domain.UserRole;
import com.video.demo.security.tokens.JwtPostProcessingToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberContext extends User {

    private Member member;

    private MemberContext(Member member, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.member = member;
    }

    public MemberContext(String username, String password, String role){
        super(username, password, parseAuthorities(role));
    }

    public static MemberContext fromMemberModel(Member member){
        return new MemberContext(member, member.getMemberEmail(), member.getMemberPw(), parseAuthorities(member.getUserRole()));
    }

    public static MemberContext fromJwtPostToken(JwtPostProcessingToken token){
        return new MemberContext(null, token.getUserEmail(), token.getPassword(), token.getAuthorities());
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role){
        return Stream.of(role).map(r-> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(String role){
        return parseAuthorities(UserRole.getRoleByName(role));
    }

    public Member getMember() {return member;}
}
