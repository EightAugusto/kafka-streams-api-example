package com.eightaugusto.kafka.streams.example.common.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** ObjectMapperConfigTest. */
public final class ObjectMapperConfigTest {

  @Test
  @DisplayName("When get ObjectMapper instance expect not null")
  public void when_get_object_mapper_instance_expect_not_null() {
    Assertions.assertNotNull(ObjectMapperConfig.getObjectMapper());
  }
}
