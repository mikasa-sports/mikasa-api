package com.mikasa.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private long accessTokenValidity;
  private long refreshTokenValidity;
  private String secretKey;

  public long getAccessTokenValidity() {
    return accessTokenValidity;
  }

  public void setAccessTokenValidity(long accessTokenValidity) {
    this.accessTokenValidity = accessTokenValidity;
  }

  public long getRefreshTokenValidity() {
    return refreshTokenValidity;
  }

  public void setRefreshTokenValidity(long refreshTokenValidity) {
    this.refreshTokenValidity = refreshTokenValidity;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }
}
