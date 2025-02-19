package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.exception.StreamDefinitionException;
import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.apache.kafka.streams.StreamsBuilder;

/** AbstractStream. */
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractStream {

  private final StreamsBuilder streamsBuilder;
  private final List<String> input;
  private final List<String> output;

  /**
   * AbstractStream constructor.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties StreamProperties.
   */
  public AbstractStream(
      @NonNull StreamsBuilder streamsBuilder, @NonNull StreamProperties streamProperties) {
    this.streamsBuilder = streamsBuilder;
    this.input = Optional.ofNullable(streamProperties.getInput()).orElse(Collections.emptyList());
    this.output = Optional.ofNullable(streamProperties.getOutput()).orElse(Collections.emptyList());
  }

  /** Abstract function for stream build trigger. */
  public abstract void buildStream();

  /**
   * Get the first source of the list.
   *
   * @return Source.
   */
  protected final String getFirstInput() {
    return getInput().stream()
        .findFirst()
        .orElseThrow(StreamDefinitionException.INVALID_SOURCE_STREAM_DEFINITION_EXCEPTION_SUPPLIER);
  }

  /**
   * Get the first destination of the list.
   *
   * @return destination.
   */
  protected final String getFirstOutput() {
    return getOutput().stream()
        .findFirst()
        .orElseThrow(
            StreamDefinitionException.INVALID_DESTINATION_STREAM_DEFINITION_EXCEPTION_SUPPLIER);
  }
}
