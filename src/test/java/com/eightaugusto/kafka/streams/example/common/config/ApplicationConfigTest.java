package com.eightaugusto.kafka.streams.example.common.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** ApplicationConfigTest. */
public final class ApplicationConfigTest {

  @Test
  @DisplayName("When get ApplicationProperties instance expect not null")
  public void when_get_application_properties_instance_expect_not_null() {
    Assertions.assertNotNull(ApplicationConfig.getApplicationProperties());
  }
}
