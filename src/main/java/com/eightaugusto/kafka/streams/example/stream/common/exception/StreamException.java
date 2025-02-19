package com.eightaugusto.kafka.streams.example.stream.common.exception;

/** StreamException. */
public class StreamException extends RuntimeException {

  /**
   * StreamException constructor.
   *
   * @param message Message.
   */
  public StreamException(String message) {
    super(message);
  }
}
