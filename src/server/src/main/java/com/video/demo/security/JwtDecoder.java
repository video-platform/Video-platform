package com.video.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.video.demo.domain.Token;
import com.video.demo.exception.InvalidJwtException;
import com.video.demo.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtDecoder {

    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtFactory jwtFactory;

    public MemberContext decodeJwt(String token, String name){
        DecodedJWT decodedJWT = isValidToken(token, name)
                .orElseThrow(() -> new InvalidJwtException("유효한 토큰이 아닙니다."));

        String username = decodedJWT.getClaim("USER_EMAIL").asString();
        String role = decodedJWT.getClaim("USER_ROLE").asString();

        return new MemberContext(username, "tempPassword", role);
    }

    private Optional<DecodedJWT> isValidToken(String token, String email){
        DecodedJWT jwt = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");
            // TODO : 시간 검증이 안된다.
            // TODO: token 검증 logic 다시한번만 더 확인
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("platform")
                    .acceptExpiresAt(3600L * 24) // 1일
                    .build();

            JWTVerifier refreshVerifier = JWT.require(algorithm)
                    .withIssuer("refresh")
                    .build();

            Token compare = tokenService.getAccessToken(token);
            if(!compare.getAccessToken().equals(token))
                throw new InvalidJwtException("유효한 토큰이 아닙니다.");

            try{
                jwt = verifier.verify(token);
            } catch (TokenExpiredException e){

            } catch (JWTVerificationException ex){
                // refresh_token
                jwt = refreshVerifier.verify(token);
                if(!compare.getRefreshToken().equals(token))
                    throw new InvalidJwtException("유효한 토큰이 아닙니다.");

                // 새로 발급해줘야 한다.
                String accessToken = jwtFactory.generateToken(compare);

            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return Optional.ofNullable(jwt);
    }

}
