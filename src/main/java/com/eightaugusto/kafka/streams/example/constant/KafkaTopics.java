package com.eightaugusto.kafka.streams.example.constant;

import java.util.Optional;
import lombok.experimental.UtilityClass;

/** KafkaTopics. */
@UtilityClass
public final class KafkaTopics {

  public static final String WORD_SPLITTER_SOURCE_TOPIC =
      Optional.ofNullable(System.getenv("KAFKA_WORD_SPLITTER_SOURCE_TOPIC"))
          .orElse("eightaugusto.plain.word");
  public static final String WORD_SPLITTER_DESTINATION_TOPIC =
      Optional.ofNullable(System.getenv("KAFKA_WORD_SPLITTER_DESTINATION_TOPIC"))
          .orElse("eightaugusto.split.word");

  public static final String WORD_LOGGER_SOURCE_TOPIC = WORD_SPLITTER_DESTINATION_TOPIC;
}
