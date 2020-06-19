package com.video.demo.security.tokens;

import com.video.demo.security.MemberContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PostAuthorizationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static PostAuthorizationToken getTokenMemberContext(MemberContext memberContext){
        return new PostAuthorizationToken(memberContext, memberContext.getPassword(), memberContext.getAuthorities());
    }

}
