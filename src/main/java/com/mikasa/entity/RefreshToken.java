package com.mikasa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity(name = "mikasa_refresh_token")
public class RefreshToken {

  @EmbeddedId
  private RefreshTokenId id;

  public RefreshToken() {
  }

  private RefreshToken(Builder builder) {
    setId(builder.id);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(RefreshToken copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    return builder;
  }

  public RefreshTokenId getId() {
    return id;
  }

  public void setId(RefreshTokenId id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RefreshToken that = (RefreshToken) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public static final class Builder {

    private RefreshTokenId id;

    private Builder() {
    }

    public Builder id(RefreshTokenId val) {
      id = val;
      return this;
    }

    public RefreshToken build() {
      return new RefreshToken(this);
    }
  }
}


