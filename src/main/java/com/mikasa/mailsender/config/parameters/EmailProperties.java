package com.mikasa.mailsender.config.parameters;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {

  private String confirmEmailPath;

  public String getConfirmEmailPath() {
    return confirmEmailPath;
  }

  public void setConfirmEmailPath(String confirmEmailPath) {
    this.confirmEmailPath = confirmEmailPath;
  }
}
