package com.video.demo.security.tokens;

import com.video.demo.domain.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtPostProcessingToken extends UsernamePasswordAuthenticationToken {

    private JwtPostProcessingToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtPostProcessingToken(String username, UserRole role){
        super(username, "tempPassword", parseAuthorities(role));
    }

    private static Collection<? extends GrantedAuthority> parseAuthorities(UserRole role){
        return Stream.of(role).map(r->new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public String getUserEmail(){
        return (String)super.getPrincipal();
    }

    public String getPassword(){
        return (String)super.getCredentials();
    }

}
