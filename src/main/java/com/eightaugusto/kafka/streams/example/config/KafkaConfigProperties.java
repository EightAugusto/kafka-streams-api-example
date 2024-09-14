package com.eightaugusto.kafka.streams.example.config;

import java.util.Optional;
import java.util.Properties;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

/** KafkaConfigProperties. */
@Log4j2
@UtilityClass
public final class KafkaConfigProperties {

  private static final String KAFKA_BOOTSTRAP_SERVERS_CONFIG = "localhost:9092";
  private static final String KAFKA_APPLICATION_ID_CONFIG = "kafka-streams-api-example";
  private static final String KAFKA_DEFAULT_KEY_SERDE_CLASS_CONFIG =
      Serdes.StringSerde.class.getName();
  private static final String KAFKA_DEFAULT_VALUE_SERDE_CLASS_CONFIG =
      Serdes.ByteArraySerde.class.getName();
  private static final int KAFKA_NUM_STREAM_THREADS_CONFIG = 2;

  /**
   * Based on environment variables or default variables get the Kafka Properties.
   *
   * @return Kafka Properties.
   */
  public static Properties getDefaultKafkaProperties() {
    return getKafkaProperties(
        Optional.ofNullable(System.getenv("KAFKA_BOOTSTRAP_SERVERS_CONFIG"))
            .orElse(KAFKA_BOOTSTRAP_SERVERS_CONFIG),
        Optional.ofNullable(System.getenv("KAFKA_APPLICATION_ID_CONFIG"))
            .orElse(KAFKA_APPLICATION_ID_CONFIG),
        Optional.ofNullable(System.getenv("KAFKA_DEFAULT_KEY_SERDE_CLASS_CONFIG"))
            .orElse(KAFKA_DEFAULT_KEY_SERDE_CLASS_CONFIG),
        Optional.ofNullable(System.getenv("KAFKA_DEFAULT_VALUE_SERDE_CLASS_CONFIG"))
            .orElse(KAFKA_DEFAULT_VALUE_SERDE_CLASS_CONFIG),
        Optional.ofNullable(System.getenv("KAFKA_NUM_STREAM_THREADS_CONFIG"))
            .map(Integer::valueOf)
            .orElse(KAFKA_NUM_STREAM_THREADS_CONFIG));
  }

  /**
   * Based on arguments get the Kafka Properties.
   *
   * @param kafkaBootstrapServer Kafka Bootstrap Server.
   * @param kafkaApplicationId Kafka Application Identification.
   * @param kafkaKeySerde Kafka Key Serde.
   * @param kafkaValueSerde Kafka Value Serde.
   * @param kafkaNumStreamThreads Kafka number of stream threads.
   * @return Kafka Properties.
   */
  public static Properties getKafkaProperties(
      String kafkaBootstrapServer,
      String kafkaApplicationId,
      String kafkaKeySerde,
      String kafkaValueSerde,
      int kafkaNumStreamThreads) {
    log.traceEntry(
        "({}, {}, {}, {}, {})",
        kafkaBootstrapServer,
        kafkaApplicationId,
        kafkaKeySerde,
        kafkaValueSerde,
        kafkaNumStreamThreads);

    final Properties kafkaProps = new Properties();
    kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
    kafkaProps.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaApplicationId);
    kafkaProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, kafkaKeySerde);
    kafkaProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, kafkaValueSerde);
    kafkaProps.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, kafkaNumStreamThreads);

    return log.traceExit(kafkaProps);
  }
}
