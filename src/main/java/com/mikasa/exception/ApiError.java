package com.mikasa.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

  private Integer errorCode;
  private String message;
  private List<String> problems;

  public ApiError() {
  }

  public ApiError(Integer errorCode, String message, List<String> problems) {
    this.errorCode = errorCode;
    this.message = message;
    this.problems = problems;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public List<String> getProblems() {
    return problems;
  }

  public void setProblems(List<String> problems) {
    this.problems = problems;
  }
}
