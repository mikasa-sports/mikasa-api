package com.mikasa.exception;

import static com.mikasa.exception.Error.INVALID_LOG_MESSAGE_PARAMETER;

import com.mikasa.util.BooleanUtils;
import com.mikasa.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException {

  protected final Error error;
  protected final HashMap<String, String> logMessageParameters;

  public BaseException(Error error) {
    this(error, Map.of());
  }

  public BaseException(Error error, Map<String, String> logMessageParameters) {
    super(error.getMessage());
    this.error = error;
    BooleanUtils.runIfTrue(error.getDynamicLogInput(),
        () -> BooleanUtils.throwIfTrue(
            CollectionUtils.emptyIfNull(error.getLogInputParameterNames()).stream()
                .anyMatch(
                    name -> !CollectionUtils.emptyIfNull(logMessageParameters).containsKey(name)),
            () -> new BaseException(INVALID_LOG_MESSAGE_PARAMETER, Map.of())
        ));
    this.logMessageParameters = new HashMap<>(CollectionUtils.emptyIfNull(logMessageParameters));
  }

  public Error getError() {
    return error;
  }

  public Map<String, String> getLogMessageParameters() {
    return logMessageParameters;
  }
}
