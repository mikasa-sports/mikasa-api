package com.mikasa.security;

import static com.mikasa.exception.Error.INSUFFICIENT_AUTHENTICATION;
import static com.mikasa.util.JsonUtil.writeToJson;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.mikasa.exception.ApiError;
import com.mikasa.exception.Error;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    sendResponse(Error.valueOf(Optional.ofNullable(response.getHeader("error"))
        .orElse(INSUFFICIENT_AUTHENTICATION.name())), response);
  }

  private void sendResponse(Error error, HttpServletResponse response) throws IOException {
    ApiError apiError =
        new ApiError(error.getCode(), error.getMessage(), List.of());

    response.setContentType(APPLICATION_JSON_VALUE);
    response.setStatus(error.getHttpStatus().value());
    response.getWriter().write(writeToJson(apiError));
  }
}
