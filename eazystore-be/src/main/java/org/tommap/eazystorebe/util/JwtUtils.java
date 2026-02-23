package org.tommap.eazystorebe.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.tommap.eazystorebe.config.JwtProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
  private final JwtProperties jwtProperties;

  @Value("${spring.application.name}")
  private String applicationName;

  public String generateToken(Authentication authentication) {
    SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    Date now = new Date();

    return Jwts.builder()
        .issuer(applicationName)
        .subject("jwt token")
        .claim("username", authentication.getName())
        .issuedAt(now)
        .expiration(new Date(now.getTime() + jwtProperties.getExpiration()))
        .signWith(secretKey)
        .compact();
  }
}
