package com.mikasa.mailsender.dto;


public class EmailRequestDto  {

  private String email;
  private String firstname;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
}
