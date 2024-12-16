package com.mikasa.dto.user;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestDto {

  @NotBlank
  private String refreshToken;

  public RefreshTokenRequestDto() {
  }

  public RefreshTokenRequestDto(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public @NotBlank String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(@NotBlank String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
