package com.mikasa.dto.user;

import static com.mikasa.dto.user.Permission.ITEM_READ;
import static com.mikasa.dto.user.Permission.ITEM_WRITE;
import static com.mikasa.dto.user.Permission.ORDER_ASSIGN;
import static com.mikasa.dto.user.Permission.ORDER_ASSIGNED_READ;
import static com.mikasa.dto.user.Permission.ORDER_READ;
import static com.mikasa.dto.user.Permission.ORDER_WRITE;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
  BUYER(Set.of(ITEM_READ, ORDER_WRITE)),
  SELLER(Set.of(ITEM_WRITE, ITEM_READ)),
  COURIER_ADMIN(Set.of(ORDER_READ, ORDER_ASSIGN)),
  COURIER(Set.of(ORDER_ASSIGNED_READ)),
  SUPER_ADMIN(Arrays.stream(Permission.values()).collect(Collectors.toSet()));

  public static final String ROLE_PATTERN = "ROLE_%s";
  private final Set<Permission> permissions;

  Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> grantedAuthorities = permissions.stream()
        .map(Permission::getValue)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());
    grantedAuthorities.add(new SimpleGrantedAuthority(String.format(ROLE_PATTERN, name())));

    return grantedAuthorities;
  }
}
