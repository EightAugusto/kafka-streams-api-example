package com.eightaugusto.kafka.streams.example.stream.common.properties;

import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class StreamPropertiesFactory {

  private static final String SOURCE_TOPIC_FORMAT = "source.topic.%d";
  private static final String DESTINATION_TOPIC_FORMAT = "destination.topic.%d";

  /**
   * Build StreamProperties based on source and destination topic size.
   *
   * @param sourceSize Source topic size.
   * @param destinationSize Destination topic size.
   * @return StreamProperties.
   */
  public static StreamProperties getStreamProperties(int sourceSize, int destinationSize) {
    final StreamProperties streamProperties = new StreamProperties();
    streamProperties.setEnabled(true);
    streamProperties.setInput(
        IntStream.range(0, sourceSize)
            .mapToObj(intValue -> String.format(SOURCE_TOPIC_FORMAT, intValue))
            .toList());
    streamProperties.setOutput(
        IntStream.range(0, destinationSize)
            .mapToObj(intValue -> String.format(DESTINATION_TOPIC_FORMAT, intValue))
            .toList());
    return streamProperties;
  }
}
