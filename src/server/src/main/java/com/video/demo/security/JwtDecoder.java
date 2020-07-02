package com.video.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.video.demo.exception.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtDecoder {

    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    public MemberContext decodeJwt(String token){
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new InvalidJwtException("유효한 토큰이 아닙니다."));
        String username = decodedJWT.getClaim("USER_EMAIL").asString();
        String role = decodedJWT.getClaim("USER_ROLE").asString();

        return new MemberContext(username, "tempPassword", role);
    }

    private Optional<DecodedJWT> isValidToken(String token){
        DecodedJWT jwt = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return Optional.ofNullable(jwt);
    }

}
