package com.mikasa.dto;

import java.util.Objects;

public class AuthenticationResponseDto {

  private String accessToken;

  private String refreshToken;

  public AuthenticationResponseDto() {
  }

  public AuthenticationResponseDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  private AuthenticationResponseDto(Builder builder) {
    setAccessToken(builder.accessToken);
    setRefreshToken(builder.refreshToken);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(AuthenticationResponseDto copy) {
    Builder builder = new Builder();
    builder.accessToken = copy.getAccessToken();
    builder.refreshToken = copy.getRefreshToken();
    return builder;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public static final class Builder {

    private String accessToken;
    private String refreshToken;

    private Builder() {
    }

    public Builder accessToken(String val) {
      accessToken = val;
      return this;
    }

    public Builder refreshToken(String val) {
      refreshToken = val;
      return this;
    }

    public AuthenticationResponseDto build() {
      return new AuthenticationResponseDto(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AuthenticationResponseDto that = (AuthenticationResponseDto) o;
    return Objects.equals(accessToken, that.accessToken) && Objects.equals(
        refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(accessToken);
    result = 31 * result + Objects.hashCode(refreshToken);
    return result;
  }
}
