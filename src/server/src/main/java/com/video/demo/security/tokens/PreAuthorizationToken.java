package com.video.demo.security.tokens;

import com.video.demo.domain.LoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public PreAuthorizationToken(LoginDto dto){
        this(dto.getId(), dto.getPassword());
    }

    public String getUsername(){
        return (String)super.getPrincipal();
    }

    public String getPassword(){
        return (String)super.getCredentials();
    }


}
