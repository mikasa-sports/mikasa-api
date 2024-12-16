package com.mikasa.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationRequestDto {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  public AuthenticationRequestDto() {
  }

  public AuthenticationRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public @NotBlank String getUsername() {
    return username;
  }

  public void setUsername(@NotBlank String username) {
    this.username = username;
  }

  public @NotBlank String getPassword() {
    return password;
  }

  public void setPassword(@NotBlank String password) {
    this.password = password;
  }
}
