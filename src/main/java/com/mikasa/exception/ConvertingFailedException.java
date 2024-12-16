package com.mikasa.exception;

public class ConvertingFailedException extends BaseException {

  public ConvertingFailedException(Error error) {
    super(error);
  }
}
