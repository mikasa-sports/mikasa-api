package com.mikasa.security;

import com.mikasa.dto.user.Role;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationManager.class);

  private final JwtTokenProvider jwtTokenProvider;

  public AuthenticationManager(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  public Authentication getAuthentication(String token) {
    SecurityUser user = this.extractUserEntityFromToken(token);
    Set<Role> roles = user.getRoles();

    log.debug("Authentication created for user: {}, userId: {}, roles: {}", user.getEmail(),
        user.getId(), roles);
    return new UsernamePasswordAuthenticationToken(user, null,
        roles.stream()
            .map(Role::getGrantedAuthorities)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet()));
  }

  private SecurityUser extractUserEntityFromToken(String token) {
    UUID userId = UUID.fromString(
        jwtTokenProvider.getClaimFromToken(token, key -> key.get("userId", String.class)));
    Set<Role> roles = Arrays.stream(Optional.ofNullable(jwtTokenProvider.getClaimFromToken(token,
                key -> key.get("roles", String.class)))
            .map(rs -> rs.split(","))
            .orElse(new String[0]))
        .map(Role::valueOf)
        .collect(Collectors.toSet());
    String name = jwtTokenProvider.getClaimFromToken(token, key -> key.get("name", String.class));
    String surname = jwtTokenProvider.getClaimFromToken(token,
        key -> key.get("surname", String.class));
    String email = jwtTokenProvider.getClaimFromToken(token, key -> key.get("email", String.class));

    return SecurityUser.builder()
        .id(userId)
        .name(name)
        .surname(surname)
        .email(email)
        .roles(roles)
        .build();
  }
}
