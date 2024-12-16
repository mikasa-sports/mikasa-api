package com.mikasa.exception;

import java.util.Map;

public class AuthenticationException extends BaseException {

  public AuthenticationException(Error error) {
    super(error);
  }

  public AuthenticationException(Error error, Map<String, String> logMessageParameters) {
    super(error, logMessageParameters);
  }
}
