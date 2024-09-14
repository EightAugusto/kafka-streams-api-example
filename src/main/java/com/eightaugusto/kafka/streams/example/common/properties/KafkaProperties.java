package com.eightaugusto.kafka.streams.example.common.properties;

import com.eightaugusto.kafka.streams.example.common.util.StringUtils;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

/** KafkaProperties. */
@Data
@NoArgsConstructor
public class KafkaProperties {

  private static final String KAFKA_DEFAULT_BOOTSTRAP_SERVERS = "localhost:9092";
  private static final String KAFKA_DEFAULT_APPLICATION_ID = "kafka-streams-api-example";

  private String bootstrapServers;
  private String applicationId;
  private String defaultKeySerdeClass;
  private String defaultValueSerdeClass;
  private Map<String, String> properties;

  /**
   * Get <code>Properties</code> based KafkaProperties.
   *
   * @return Properties.
   */
  public Properties getKafkaProperties() {
    final Properties kafkaProperties = new Properties();

    Optional.ofNullable(properties)
        .filter(properties -> !properties.isEmpty())
        .ifPresent(kafkaProperties::putAll);

    kafkaProperties.put(
        StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
        Optional.ofNullable(bootstrapServers)
            .filter(StringUtils::isNotBlank)
            .orElse(KAFKA_DEFAULT_BOOTSTRAP_SERVERS));
    kafkaProperties.put(
        StreamsConfig.APPLICATION_ID_CONFIG,
        Optional.ofNullable(applicationId)
            .filter(StringUtils::isNotBlank)
            .orElse(KAFKA_DEFAULT_APPLICATION_ID));

    kafkaProperties.put(
        StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
        Optional.ofNullable(defaultKeySerdeClass)
            .filter(StringUtils::isNotBlank)
            .orElse(Serdes.StringSerde.class.getName()));
    kafkaProperties.put(
        StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
        Optional.ofNullable(defaultValueSerdeClass)
            .filter(StringUtils::isNotBlank)
            .orElse(Serdes.StringSerde.class.getName()));

    return kafkaProperties;
  }
}
