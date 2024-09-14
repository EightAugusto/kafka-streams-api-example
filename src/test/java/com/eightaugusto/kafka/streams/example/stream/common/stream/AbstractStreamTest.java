package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.exception.StreamDefinitionException;
import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;

/** AbstractStreamTest. */
@Log4j2
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractStreamTest<T extends AbstractStream> {

  private final TopologyTestDriver topology;
  private final T stream;
  private final List<String> input;
  private final List<String> output;

  public AbstractStreamTest(
      StreamProperties streamProperties,
      BiFunction<StreamsBuilder, StreamProperties, T> streamBiFunction) {
    final StreamsBuilder streamsBuilder = new StreamsBuilder();

    this.stream = streamBiFunction.apply(streamsBuilder, streamProperties);
    this.input = Optional.ofNullable(streamProperties.getInput()).orElse(Collections.emptyList());
    this.output = Optional.ofNullable(streamProperties.getOutput()).orElse(Collections.emptyList());

    this.stream.buildStream();
    this.topology = new TopologyTestDriver(streamsBuilder.build());
  }

  @AfterEach
  protected final void close() {
    try {
      topology.close();
    } catch (Exception ex) {
      log.error("Error when trying to close stream '{}': {}", getClass(), ex);
    }
  }

  /**
   * Get the first source of the list.
   *
   * @return Source.
   */
  protected final String getFirstSource() {
    return input.stream()
        .findFirst()
        .orElseThrow(StreamDefinitionException.INVALID_SOURCE_STREAM_DEFINITION_EXCEPTION_SUPPLIER);
  }

  /**
   * Get the first destination of the list.
   *
   * @return destination.
   */
  protected final String getFirstDestination() {
    return output.stream()
        .findFirst()
        .orElseThrow(
            StreamDefinitionException.INVALID_DESTINATION_STREAM_DEFINITION_EXCEPTION_SUPPLIER);
  }
}
