package com.eightaugusto.kafka.streams.example.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import com.eightaugusto.kafka.streams.example.stream.common.stream.AbstractStream;
import com.eightaugusto.kafka.streams.example.stream.wordlogger.stream.WordLogger;
import com.eightaugusto.kafka.streams.example.stream.wordsplitter.stream.WordSplitter;
import java.util.Map;
import java.util.function.BiFunction;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.StreamsBuilder;

/** StreamFactory. */
@Log4j2
@UtilityClass
public final class StreamFactory {

  private static final Map<String, BiFunction<StreamsBuilder, StreamProperties, AbstractStream>>
      STREAM_FACTORY_MAP =
          Map.of(
              WordSplitter.class.getSimpleName().toLowerCase(), WordSplitter::new,
              WordLogger.class.getSimpleName().toLowerCase(), WordLogger::new);

  /**
   * Build stream based on StreamsBuilder and AbstractStreamProperties.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties AbstractStreamProperties.
   */
  public static void buildStream(
      StreamsBuilder streamsBuilder, String streamId, StreamProperties streamProperties) {
    if (streamProperties == null) {
      log.error("Invalid null properties for '{}'", streamId);
      return;
    }

    if (!streamProperties.isEnabled()) {
      log.debug("'{}' disabled stream", streamId);
      return;
    }

    log.info("'{}' enabled stream with properties '{}'", streamId, streamProperties);
    STREAM_FACTORY_MAP
        .get(streamId.toLowerCase())
        .apply(streamsBuilder, streamProperties)
        .buildStream();
  }
}
