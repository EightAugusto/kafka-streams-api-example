package com.eightaugusto.kafka.streams.example.stream.common.exception;

import java.util.function.Supplier;

/** StreamDefinitionException. */
public class StreamDefinitionException extends StreamException {

  private static final String INVALID_SOURCE_STREAM_DEFINITION_MESSAGE =
      "Invalid source stream definition";
  private static final String INVALID_DESTINATION_STREAM_DEFINITION_MESSAGE =
      "Invalid destination stream definition";

  public static final Supplier<StreamDefinitionException>
      INVALID_SOURCE_STREAM_DEFINITION_EXCEPTION_SUPPLIER =
          () -> new StreamDefinitionException(INVALID_SOURCE_STREAM_DEFINITION_MESSAGE);
  public static final Supplier<StreamDefinitionException>
      INVALID_DESTINATION_STREAM_DEFINITION_EXCEPTION_SUPPLIER =
          () -> new StreamDefinitionException(INVALID_DESTINATION_STREAM_DEFINITION_MESSAGE);

  /**
   * StreamDefinitionException constructor.
   *
   * @param message Message.
   */
  public StreamDefinitionException(String message) {
    super(message);
  }
}
