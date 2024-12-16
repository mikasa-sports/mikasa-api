package com.mikasa.config.properties;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "permitted.urls")
public class SecurityProperties {
  private List<String> mutations = new ArrayList<>();

  public List<String> getMutations() {
    return mutations;
  }

  public void setMutations(List<String> mutations) {
    this.mutations = mutations;
  }
}
