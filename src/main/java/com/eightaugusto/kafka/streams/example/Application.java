package com.eightaugusto.kafka.streams.example;

import com.eightaugusto.kafka.streams.example.config.KafkaConfigProperties;
import com.eightaugusto.kafka.streams.example.mapper.WordMapper;
import com.eightaugusto.kafka.streams.example.stream.WordLogger;
import com.eightaugusto.kafka.streams.example.stream.WordSplitter;
import java.util.Properties;
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
    final Properties kafkaProperties = KafkaConfigProperties.getDefaultKafkaProperties();
    final StreamsBuilder streamsBuilder = new StreamsBuilder();

    new WordSplitter(WordMapper.MAPPER).build(streamsBuilder);
    new WordLogger().build(streamsBuilder);

    final KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), kafkaProperties);
    kafkaStreams.cleanUp();
    kafkaStreams.start();
    Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
  }
}
