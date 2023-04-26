package ru.siarheyeu.springcourse.FirstSecurityApp.security;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    public String generateToken(String username){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("siarheyeu")
                .withExpiresAt(expirationDate);
    }

}
