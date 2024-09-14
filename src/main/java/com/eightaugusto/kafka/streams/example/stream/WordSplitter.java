package com.eightaugusto.kafka.streams.example.stream;

import com.eightaugusto.kafka.streams.example.constant.KafkaTopics;
import com.eightaugusto.kafka.streams.example.dto.WordDto;
import com.eightaugusto.kafka.streams.example.mapper.WordMapper;
import com.eightaugusto.kafka.streams.example.serde.jackson.JacksonSerde;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

/** WordSplitter. */
@Log4j2
public final class WordSplitter {

  private final WordMapper mapper;

  public WordSplitter(WordMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * Based on the Stream, split the String by spaces, map into an object and produce a message per
   * word.
   *
   * @param streamsBuilder StreamsBuilder.
   */
  public void build(StreamsBuilder streamsBuilder) {
    streamsBuilder.stream(
            KafkaTopics.WORD_SPLITTER_SOURCE_TOPIC, Consumed.with(Serdes.String(), Serdes.String()))
        .filter((key, value) -> value != null && !value.isBlank())
        .flatMapValues(value -> Arrays.asList(value.split("\\s+")))
        .mapValues(mapper::convert)
        .to(
            KafkaTopics.WORD_SPLITTER_DESTINATION_TOPIC,
            Produced.with(Serdes.String(), JacksonSerde.of(WordDto.class)));
  }
}
