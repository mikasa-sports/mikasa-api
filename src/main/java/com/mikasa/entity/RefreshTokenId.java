package com.mikasa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RefreshTokenId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "refresh_token", nullable = false)
  private String refreshToken;

  public RefreshTokenId() {
  }

  private RefreshTokenId(Builder builder) {
    setUser(builder.user);
    setRefreshToken(builder.refreshToken);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(RefreshTokenId copy) {
    Builder builder = new Builder();
    builder.user = copy.getUser();
    builder.refreshToken = copy.getRefreshToken();
    return builder;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RefreshTokenId that = (RefreshTokenId) o;
    return Objects.equals(user, that.user) && Objects.equals(refreshToken,
        that.refreshToken);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(user);
    result = 31 * result + Objects.hashCode(refreshToken);
    return result;
  }

  public static final class Builder {

    private User user;
    private String refreshToken;

    private Builder() {
    }

    public Builder user(User val) {
      user = val;
      return this;
    }

    public Builder refreshToken(String val) {
      refreshToken = val;
      return this;
    }

    public RefreshTokenId build() {
      return new RefreshTokenId(this);
    }
  }
}