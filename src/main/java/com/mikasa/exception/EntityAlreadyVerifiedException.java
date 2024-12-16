package com.mikasa.exception;

public class EntityAlreadyVerifiedException extends BaseException {

  public EntityAlreadyVerifiedException(Error error) {
    super(error);
  }
}
