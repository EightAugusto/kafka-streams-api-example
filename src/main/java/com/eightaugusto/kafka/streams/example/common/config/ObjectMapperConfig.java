package com.eightaugusto.kafka.streams.example.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/** ObjectMapperConfig. */
@UtilityClass
public final class ObjectMapperConfig {

  private static ObjectMapper OBJECT_MAPPER;

  /**
   * Get an ObjectMapper instance.
   *
   * @return Singleton ObjectMapper.
   */
  public static ObjectMapper getObjectMapper() {
    if (OBJECT_MAPPER == null) {
      OBJECT_MAPPER = new ObjectMapper();
    }
    return OBJECT_MAPPER;
  }
}
