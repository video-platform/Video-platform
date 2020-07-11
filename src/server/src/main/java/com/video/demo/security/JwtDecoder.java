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
            // TODO : 시간 validate 추가 및 refresh token
            // 시간이 만료되었으면 refresh token을 검증한다.
            // refresh token이 맞으면 다시 토큰 생성해서 내려주고, 원래 행동을 하게 해준다.
            // refresh token은 시간 검증을 할 필요가 없다.
            // verity가 누가 갖고 있던 상관 없이 토큰이 유효한지만 검증해주는 듯 하다.
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("platform")
                    .acceptExpiresAt(10800) // 3시간
                    .build();

            jwt = verifier.verify(token);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return Optional.ofNullable(jwt);
    }

}
