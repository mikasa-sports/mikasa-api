package com.mikasa.security;

import com.mikasa.dto.user.Role;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class SecurityUser {

  private UUID id;
  private String name;
  private String surname;
  private String email;
  private Set<Role> roles;

  private SecurityUser(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setSurname(builder.surname);
    setEmail(builder.email);
    roles = builder.roles;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(SecurityUser copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.name = copy.getName();
    builder.surname = copy.getSurname();
    builder.email = copy.getEmail();
    builder.roles = copy.getRoles();
    return builder;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRole(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SecurityUser that = (SecurityUser) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(surname, that.surname) && Objects.equals(email, that.email)
        && Objects.equals(
        roles, that.roles);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(name);
    result = 31 * result + Objects.hashCode(surname);
    result = 31 * result + Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(roles);
    return result;
  }

  public static final class Builder {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Set<Role> roles;

    private Builder() {
    }

    public Builder id(UUID val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder surname(String val) {
      surname = val;
      return this;
    }

    public Builder email(String val) {
      email = val;
      return this;
    }

    public Builder roles(Set<Role> val) {
      roles = val;
      return this;
    }

    public SecurityUser build() {
      return new SecurityUser(this);
    }
  }
}
