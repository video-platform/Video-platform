package com.video.demo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public String getUsername(){
        return (String)super.getPrincipal();
    }

    public String getPassword(){
        return (String)super.getCredentials();
    }


}
