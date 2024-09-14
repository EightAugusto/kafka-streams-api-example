package com.eightaugusto.kafka.streams.example;

import com.eightaugusto.kafka.streams.example.common.config.ApplicationConfig;
import com.eightaugusto.kafka.streams.example.common.properties.ApplicationProperties;
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
    final StreamsBuilder streamsBuilder = new StreamsBuilder();

    Optional.ofNullable(applicationProperties.getStream())
        .filter(stringStreamPropertiesMap -> !stringStreamPropertiesMap.isEmpty())
        .orElseThrow()
        .forEach((key, value) -> StreamFactory.buildStream(streamsBuilder, key, value));

    final KafkaStreams kafkaStreams =
        new KafkaStreams(
            streamsBuilder.build(), applicationProperties.getKafka().getKafkaProperties());
    kafkaStreams.cleanUp();
    kafkaStreams.start();
    Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
  }
}
