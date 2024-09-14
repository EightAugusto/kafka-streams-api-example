package com.eightaugusto.kafka.streams.example.common.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** YamlConfigTest. */
public final class YamlConfigTest {

  @Test
  @DisplayName("When get Yaml instance expect not null")
  public void when_get_yaml_instance_expect_not_null() {
    Assertions.assertNotNull(YamlConfig.getYaml());
  }
}
