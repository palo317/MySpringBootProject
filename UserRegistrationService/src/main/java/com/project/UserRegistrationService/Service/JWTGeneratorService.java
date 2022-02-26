package com.project.UserRegistrationService.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTGeneratorService {

    @Value("${jwt.secret}")
    private String secretKey;
    private static final long  VALIDITY_PERIOD= System.currentTimeMillis()+60*60*1000;


    public String generateToken(String email,String role){
         String token=Jwts.builder()
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_PERIOD))
                 .setSubject(email)
                 .setAudience(role)
                 .setIssuer("com.project")
                 .signWith(SignatureAlgorithm.HS512,secretKey)
                 .compact();

         return token;


    }


}
