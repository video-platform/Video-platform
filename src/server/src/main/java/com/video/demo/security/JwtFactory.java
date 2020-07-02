package com.video.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {

    private static final Logger logger = LoggerFactory.getLogger(JwtFactory.class);
    private static final String SIGNINGKEY = "secret";

    public String generateToken(MemberContext memberContext){
        String token = null;
        try{
            token = JWT.create()
                        .withIssuer("platform")
                        .withClaim("USER_EMAIL", memberContext.getUsername())
                        .withClaim("USER_NAME", memberContext.getMember().getMemberName())
                        .withClaim("USER_ROLE", memberContext.getMember().getUserRole().getRoleName())
                        .sign(generateAlgorithm());
        }catch (Exception ignored){
            logger.error(ignored.getMessage());
        }
        return token;
    }

    private Algorithm generateAlgorithm(){
        return Algorithm.HMAC256(SIGNINGKEY);
    }

}
