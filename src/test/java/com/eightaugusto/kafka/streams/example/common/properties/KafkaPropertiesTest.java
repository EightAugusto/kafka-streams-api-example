package com.eightaugusto.kafka.streams.example.common.properties;

import java.util.Properties;
import org.apache.kafka.streams.StreamsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** KafkaPropertiesTest. */
public final class KafkaPropertiesTest {

  @Test
  @DisplayName("When get KafkaProperties instance expect not null with default")
  public void when_get_kafka_properties_instance_expect_not_null_with_default() {
    final KafkaProperties kafkaProperties = new KafkaProperties();
    Assertions.assertNotNull(kafkaProperties);

    final Properties properties = kafkaProperties.getKafkaProperties();
    Assertions.assertNotNull(properties.get(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG));
    Assertions.assertNotNull(properties.get(StreamsConfig.APPLICATION_ID_CONFIG));
    Assertions.assertNotNull(properties.get(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG));
    Assertions.assertNotNull(properties.get(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG));
  }
}
