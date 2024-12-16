package com.mikasa.entity;

import com.mikasa.dto.user.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "mikasa_user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  private String password;

  @ColumnDefault("false")
  private boolean emailVerified;

  @CreationTimestamp
  @Column(nullable = false)
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  public User() {
  }

  public User(UUID id, String name, String surname, String phone, String email, Role role,
      String password, boolean emailVerified, Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.email = email;
    this.role = role;
    this.password = password;
    this.emailVerified = emailVerified;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  private User(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setSurname(builder.surname);
    setPhone(builder.phone);
    setEmail(builder.email);
    setRole(builder.role);
    setPassword(builder.password);
    setEmailVerified(builder.emailVerified);
    setCreatedAt(builder.createdAt);
    setUpdatedAt(builder.updatedAt);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(User copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.name = copy.getName();
    builder.surname = copy.getSurname();
    builder.phone = copy.getPhone();
    builder.email = copy.getEmail();
    builder.role = copy.getRole();
    builder.password = copy.getPassword();
    builder.emailVerified = copy.isEmailVerified();
    builder.createdAt = copy.getCreatedAt();
    builder.updatedAt = copy.getUpdatedAt();
    return builder;
  }

  public Builder toBuilder() {
    return new Builder(this);
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
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


  public static final class Builder {

    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Role role;
    private String password;
    private boolean emailVerified;
    private Instant createdAt;
    private Instant updatedAt;

    private Builder() {
    }

    private Builder(User user) {
      this.id = user.id;
      this.name = user.name;
      this.surname = user.surname;
      this.phone = user.phone;
      this.email = user.email;
      this.role = user.role;
      this.password = user.password;
      this.emailVerified = user.emailVerified;
      this.createdAt = user.createdAt;
      this.updatedAt = user.updatedAt;
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

    public Builder password(String val) {
      password = val;
      return this;
    }

    public Builder emailVerified(boolean val) {
      emailVerified = val;
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

    public User build() {
      return new User(this);
    }
  }
}
