package com.eightaugusto.kafka.streams.example.stream;

import com.eightaugusto.kafka.streams.example.constant.KafkaTopics;
import com.eightaugusto.kafka.streams.example.dto.WordDto;
import com.eightaugusto.kafka.streams.example.serde.jackson.JacksonSerde;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;

/** WordLogger. */
@Log4j2
public final class WordLogger {

  /**
   * Based on Stream, log the <code>WordDto</code>.
   *
   * @param streamsBuilder StreamsBuilder.
   */
  public void build(StreamsBuilder streamsBuilder) {
    streamsBuilder.stream(
            KafkaTopics.WORD_LOGGER_SOURCE_TOPIC,
            Consumed.with(Serdes.String(), JacksonSerde.of(WordDto.class)))
        .filter((key, value) -> value != null)
        .peek((key, value) -> log.info(value));
  }
}
