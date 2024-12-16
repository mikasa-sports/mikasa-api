package com.mikasa.exception;

import java.util.Map;

public class EntityAlreadyExistException extends BaseException {

  public EntityAlreadyExistException(Error error) {
    super(error);
  }

  public EntityAlreadyExistException(Error error, Map<String, String> logMessageParameters) {
    super(error, logMessageParameters);
  }
}
