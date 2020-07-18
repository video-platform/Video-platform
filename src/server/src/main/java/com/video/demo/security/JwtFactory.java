package com.video.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class JwtFactory {

    private static final Logger logger = LoggerFactory.getLogger(JwtFactory.class);
    private static final String SIGNINGKEY = "secret";
    private static final LocalDateTime EXPIRES_LOCAL = LocalDateTime.now();


    private static final Date EXPIRES_DATE = Date.from(EXPIRES_LOCAL.toInstant(ZoneOffset.ofHoursMinutes(0, 5)));


    public String generateToken(MemberContext memberContext){
        String token = null;
        try{
            token = JWT.create()
                        .withIssuer("platform")
                        .withClaim("USER_EMAIL", memberContext.getUsername())
                        .withClaim("USER_NAME", memberContext.getMember().getMemberName())
                        .withClaim("USER_ROLE", memberContext.getMember().getUserRole().getRoleName())
                        .withExpiresAt(EXPIRES_DATE)
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
