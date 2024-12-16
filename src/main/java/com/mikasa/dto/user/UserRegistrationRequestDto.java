package com.mikasa.dto.user;

import com.mikasa.validations.constraint.Name;
import com.mikasa.validations.constraint.Password;
import com.mikasa.validations.constraint.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationRequestDto implements UserDto {

  @Name
  private String name;

  @Name
  private String surname;

  @Phone
  @NotNull
  private String phone;

  @Email
  @NotNull
  private String email;

  @NotNull
  @Password
  private String password;

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

  public @NotNull String getPhone() {
    return phone;
  }

  public void setPhone(@NotNull String phone) {
    this.phone = phone;
  }

  public @Email @NotNull String getEmail() {
    return email;
  }

  public void setEmail(
      @Email @NotNull String email) {
    this.email = email;
  }

  public @NotNull String getPassword() {
    return password;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }
}
