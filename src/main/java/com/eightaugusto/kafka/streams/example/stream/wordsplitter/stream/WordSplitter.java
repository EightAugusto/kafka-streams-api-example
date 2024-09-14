package com.eightaugusto.kafka.streams.example.stream.wordsplitter.stream;

import com.eightaugusto.kafka.streams.example.common.serde.jackson.JacksonSerde;
import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import com.eightaugusto.kafka.streams.example.stream.common.stream.AbstractProducer;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import com.eightaugusto.kafka.streams.example.stream.word.mapper.WordMapper;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

/** WordSplitter. */
@Log4j2
public final class WordSplitter extends AbstractProducer<String, String, String, WordDto> {

  private static final String SPACE_REGEX = "\\s+";

  /**
   * WordLogger constructor.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties StreamProperties.
   */
  public WordSplitter(StreamsBuilder streamsBuilder, StreamProperties streamProperties) {
    super(streamsBuilder, streamProperties);
  }

  @Override
  public KStream<String, WordDto> apply(KStream<String, String> source) {
    return source
        .filter((key, value) -> value != null && !value.isBlank())
        .flatMapValues(value -> Arrays.asList(value.split(SPACE_REGEX)))
        .mapValues(WordMapper.MAPPER::convert);
  }

  @Override
  protected Serde<String> getSourceKeySerde() {
    return Serdes.String();
  }

  @Override
  protected Serde<String> getSourceValueSerde() {
    return Serdes.String();
  }

  @Override
  protected Serde<String> getDestinationKeySerde() {
    return Serdes.String();
  }

  @Override
  protected Serde<WordDto> getDestinationValueSerde() {
    return JacksonSerde.of(WordDto.class);
  }
}
