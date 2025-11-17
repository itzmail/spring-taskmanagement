package com.aidecetest.task_management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  private final Key jwtScret = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  private final long jwtExirationMs = 3600000;

  public String generateToken(String username) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExirationMs);

    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(jwtScret)
      .compact();
  }

  public String getUsernameFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(jwtScret)
      .build()
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
        .setSigningKey(jwtScret)
        .build()
        .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
