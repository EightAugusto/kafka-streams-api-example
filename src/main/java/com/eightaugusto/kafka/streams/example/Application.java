package com.eightaugusto.kafka.streams.example;

import com.eightaugusto.kafka.streams.example.common.config.ApplicationConfig;
import com.eightaugusto.kafka.streams.example.common.properties.ApplicationProperties;
import com.eightaugusto.kafka.streams.example.common.properties.KafkaProperties;
import com.eightaugusto.kafka.streams.example.stream.StreamFactory;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

/** Application. */
@Log4j2
public class Application {

  /**
   * Basic entry-point.
   *
   * @param args Arguments.
   */
  public static void main(String[] args) {
    final ApplicationProperties applicationProperties =
        ApplicationConfig.getApplicationProperties();
    log.info("Starting Kafka Streams with properties: '{}'", applicationProperties);

    final StreamsBuilder streamsBuilder = new StreamsBuilder();

    Optional.ofNullable(applicationProperties.getStream())
        .filter(stringStreamPropertiesMap -> !stringStreamPropertiesMap.isEmpty())
        .orElseThrow()
        .forEach((key, value) -> StreamFactory.buildStream(streamsBuilder, key, value));

    final KafkaStreams kafkaStreams =
        Optional.ofNullable(applicationProperties.getKafka())
            .map(KafkaProperties::getKafkaProperties)
            .map(kafkaProperties -> new KafkaStreams(streamsBuilder.build(), kafkaProperties))
            .orElseThrow();
    kafkaStreams.cleanUp();
    kafkaStreams.start();
    Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
  }
}
