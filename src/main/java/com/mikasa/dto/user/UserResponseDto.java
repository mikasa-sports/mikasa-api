package com.mikasa.dto.user;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class UserResponseDto {

  private UUID id;
  private String name;
  private String surname;
  private String phone;
  private String email;
  private Role role;
  private Instant createdAt;
  private Instant updatedAt;

  public UserResponseDto() {
  }

  private UserResponseDto(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setSurname(builder.surname);
    setPhone(builder.phone);
    setEmail(builder.email);
    setRole(builder.role);
    setCreatedAt(builder.createdAt);
    setUpdatedAt(builder.updatedAt);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(UserResponseDto copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.name = copy.getName();
    builder.surname = copy.getSurname();
    builder.phone = copy.getPhone();
    builder.email = copy.getEmail();
    builder.role = copy.getRole();
    builder.createdAt = copy.getCreatedAt();
    builder.updatedAt = copy.getUpdatedAt();
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserResponseDto that = (UserResponseDto) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(surname, that.surname) && Objects.equals(phone, that.phone)
        && Objects.equals(
        email, that.email) && role == that.role && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(name);
    result = 31 * result + Objects.hashCode(surname);
    result = 31 * result + Objects.hashCode(phone);
    result = 31 * result + Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(role);
    result = 31 * result + Objects.hashCode(createdAt);
    result = 31 * result + Objects.hashCode(updatedAt);
    return result;
  }

  public static final class Builder {

    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;

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

    public Builder phone(String val) {
      phone = val;
      return this;
    }

    public Builder email(String val) {
      email = val;
      return this;
    }

    public Builder role(Role val) {
      role = val;
      return this;
    }

    public Builder createdAt(Instant val) {
      createdAt = val;
      return this;
    }

    public Builder updatedAt(Instant val) {
      updatedAt = val;
      return this;
    }

    public UserResponseDto build() {
      return new UserResponseDto(this);
    }
  }
}
