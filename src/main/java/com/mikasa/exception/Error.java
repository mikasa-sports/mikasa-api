package com.mikasa.exception;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.slf4j.event.Level.INFO;
import static org.slf4j.event.Level.WARN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Set;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

/**
 * Error contains: CODE a unique code which value's first 3 numbers represent the response status
 * code. HTTP STATUS the response status code MESSAGE the error message
 */
public enum Error {

  INVALID_LOG_MESSAGE_PARAMETER(4001, BAD_REQUEST,
      "Missing required log input parameters", "", null, FALSE, Set.of()),

  JWT_AUTHENTICATION(4011, UNAUTHORIZED,
      "Invalid or expired token. Please re-authenticate.", "", null, FALSE, Set.of()),
  INVALID_CREDENTIALS(4012, UNAUTHORIZED, "Incorrect username or password",
      "[username] : Provided wrong credentials for authentication", WARN, TRUE, Set.of("username")),
  NOT_ACTIVE_ACCOUNT(4013, UNAUTHORIZED, "User Email Is Not Verified", "", null, FALSE, Set.of()),
  INVALID_REFRESH_TOKEN(4013, UNAUTHORIZED, "Invalid or expired refresh token.", "", null, FALSE,
      Set.of()),
  //  ACCESS_RESTRICTED(4014, UNAUTHORIZED, "Access restricted to specific paths."),
  INSUFFICIENT_AUTHENTICATION(4015, UNAUTHORIZED,
      "Authentication credentials are required to access this resource", "", null, FALSE, Set.of()),

//  ACCESS_DENIED(4031, FORBIDDEN, "Permission Denied To Requested Resource"),

//  CONFIRM_TOKEN_NOT_FOUND(4041, NOT_FOUND, "Confirmation Token Not Found"),

  USER_ALREADY_EXIST_WITH_EMAIL(4091, CONFLICT, "There Is a User Registered With Such Email",
      "Invalid email! There is an user created with such email: '[email]'", INFO, TRUE,
      Set.of("email")),
  USER_ALREADY_EXIST_WITH_PHONE(
      4092, CONFLICT, "There Is a User Registered With Such Phone Number",
      "Invalid phone number! There is an user created with such phone number '[phone]'",
      INFO, TRUE, Set.of("phone")),
  USER_ALREADY_VERIFIED(
      4094, CONFLICT, "Your account has already been verified. No further action is needed.", null,
      null, FALSE, Set.of()),

  FAILED_JSON_CONVERTING(5001, INTERNAL_SERVER_ERROR, "Error Occurred While Parsing To Json", null,
      null, FALSE, Set.of());

  private final Integer code;
  private final HttpStatus httpStatus;
  private final String message;
  private final String logMessage;
  private final Level level;
  private final Boolean dynamicLogInput;
  private final Set<String> logInputParameterNames;

  public Integer getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public String getLogMessage() {
    return logMessage;
  }

  public Level getLevel() {
    return level;
  }

  public Boolean getDynamicLogInput() {
    return dynamicLogInput;
  }

  public Set<String> getLogInputParameterNames() {
    return logInputParameterNames;
  }

  Error(Integer code, HttpStatus httpStatus, String message, String logMessage, Level level,
      Boolean dynamicLogInput, Set<String> logInputParameterNames) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
    this.logMessage = logMessage;
    this.level = level;
    this.dynamicLogInput = dynamicLogInput;
    this.logInputParameterNames = logInputParameterNames;
  }
}
