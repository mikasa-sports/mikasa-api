package com.mikasa.util;

import static com.mikasa.exception.Error.FAILED_JSON_CONVERTING;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mikasa.exception.ConvertingFailedException;

public final class JsonUtil {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private JsonUtil() {
  }

  public static String writeToJson(Object object) {
    try {
      OBJECT_MAPPER.registerModule(new JavaTimeModule());
      OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new ConvertingFailedException(FAILED_JSON_CONVERTING);
    }
  }
}
