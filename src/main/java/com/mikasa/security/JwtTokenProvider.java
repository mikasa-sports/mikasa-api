package com.mikasa.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mikasa.config.properties.JwtProperties;
import com.mikasa.dto.JwtTokenType;
import com.mikasa.entity.User;
import com.mikasa.exception.Error;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  public static final String USER_ID = "userId";
  public static final String USER_ROLE = "userRole";

  private final JwtProperties jwtProperties;

  public JwtTokenProvider(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
  }

  public String createJwt(User user, JwtTokenType tokenType) {
    long validityMinutes =
        (tokenType == JwtTokenType.ACCESS_TOKEN) ? jwtProperties.getAccessTokenValidity()
            : jwtProperties.getRefreshTokenValidity();

    return JWT
        .create()
        .withSubject(user.getEmail())
        .withClaim(USER_ID, user.getId().toString())
        .withClaim(USER_ROLE, user.getRole().name())
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plus(validityMinutes, ChronoUnit.MINUTES))
        .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
  }

  public String resolveToken(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION);
    return header == null ? null : header.replace("Bearer", "").strip();
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts
        .parser()
        .build()
        .parseSignedClaims(token)
        .getPayload();

    return claimsResolver.apply(claims);
  }

  public boolean validateToken(String token, HttpServletRequest servletRequest) {
    try {
      return this.getClaimFromToken(token, Claims::getExpiration).after(new Date());
    } catch (JwtException e) {
      servletRequest.setAttribute("error", Error.JWT_AUTHENTICATION.getMessage());
      return false;
    }
  }
}
