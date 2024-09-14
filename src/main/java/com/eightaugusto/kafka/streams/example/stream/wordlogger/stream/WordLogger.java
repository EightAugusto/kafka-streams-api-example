package com.eightaugusto.kafka.streams.example.stream.wordlogger.stream;

import com.eightaugusto.kafka.streams.example.common.serde.jackson.JacksonSerde;
import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import com.eightaugusto.kafka.streams.example.stream.common.stream.AbstractConsumer;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

/** WordLogger. */
@Log4j2
public final class WordLogger extends AbstractConsumer<String, WordDto> {

  /**
   * WordLogger constructor.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties StreamProperties.
   */
  public WordLogger(StreamsBuilder streamsBuilder, StreamProperties streamProperties) {
    super(streamsBuilder, streamProperties);
  }

  @Override
  public void accept(KStream<String, WordDto> source) {
    source.filter((key, value) -> value != null).peek((key, value) -> log.info(value));
  }

  @Override
  public Serde<String> getKeySerde() {
    return Serdes.String();
  }

  @Override
  public Serde<WordDto> getValueSerde() {
    return JacksonSerde.of(WordDto.class);
  }
}
