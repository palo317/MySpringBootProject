package com.learn.apigateway.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JWTValidationFilter implements GatewayFilter {
    @Value("${jwt.secret}")
    private String jwtSecret;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //Check for existence of Auth Header
        if(!request.getHeaders().containsKey(AUTHORIZATION)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String authHeader = request.getHeaders().getOrEmpty(AUTHORIZATION).get(0);

        //Check if Auth header has bearer scheme
        if(!authHeader.startsWith(BEARER)){
            logger.error("Invalid Authorization Header");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //Extract the token
        String jwtToken = authHeader.substring(7);

        //Parse and Validate the token signature with secret key and get the claims
        try {
            Claims claims = getClaims(jwtToken);
            String email = claims.getSubject();
//            System.out.println(email);
            String audience = claims.getAudience();
//            System.out.println(audience);
            request.mutate().header("useremail", email);
        } catch (JwtException e) {
            logger.error("Invalid Token", e);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }


    private Claims getClaims(String jwtToken){
        logger.debug(jwtSecret);
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}

