package com.mikasa.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.mikasa.config.properties.SecurityProperties;
import com.mikasa.security.AuthenticationFilter;
import com.mikasa.security.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final AuthenticationFilter authenticationFilter;
  private final JwtAuthenticationEntryPoint authenticationEntryPoint;
  private final SecurityProperties properties;

  public SecurityConfig(AuthenticationFilter authenticationFilter,
      JwtAuthenticationEntryPoint authenticationEntryPoint, SecurityProperties properties) {
    this.authenticationFilter = authenticationFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.properties = properties;
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(properties.getMutations().toArray(new String[0]))
            .permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults())
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
