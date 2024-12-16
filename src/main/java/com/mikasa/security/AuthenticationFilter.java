package com.mikasa.security;

import com.mikasa.exception.AuthenticationException;
import com.mikasa.exception.Error;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public AuthenticationFilter(JwtTokenProvider jwtTokenProvider,
      AuthenticationManager authenticationManager) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(request);

    try {
      if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token, request)) {
        Authentication authentication = authenticationManager.getAuthentication(token);
        if (authentication != null) {
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      filterChain.doFilter(request, response);
    } catch (AuthenticationException e) {
      log.error("JWT Authentication Exception: {}", e.getMessage());
      request.setAttribute("error", Error.JWT_AUTHENTICATION.getMessage());
    } finally {
      SecurityContextHolder.clearContext();
    }
  }
}
