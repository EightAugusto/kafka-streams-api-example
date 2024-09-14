package com.eightaugusto.kafka.streams.example.stream.wordlogger.stream;

import com.eightaugusto.kafka.streams.example.stream.common.exception.StreamDefinitionException;
import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamPropertiesFactory;
import com.eightaugusto.kafka.streams.example.stream.common.stream.AbstractConsumerTest;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import com.eightaugusto.kafka.streams.example.stream.word.mapper.WordMapper;
import java.util.UUID;
import org.apache.kafka.streams.StreamsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** WordLoggerTest. */
public class WordLoggerTest extends AbstractConsumerTest<String, WordDto> {

  public WordLoggerTest() {
    super(StreamPropertiesFactory.getStreamProperties(1, 0), WordLogger::new);
  }

  @Test
  @DisplayName("When send input record expect no error")
  public void when_send_input_record_expect_no_error() {
    getInputTopic().pipeInput(WordMapper.MAPPER.convert(UUID.randomUUID().toString()));
  }

  @Test
  @DisplayName("When instance stream with invalid properties expect StreamDefinitionException")
  public void when_instance_stream_with_invalid_properties_expect_stream_definition_exception() {
    Assertions.assertThrows(
        StreamDefinitionException.class,
        () ->
            new WordLogger(new StreamsBuilder(), StreamPropertiesFactory.getStreamProperties(0, 0))
                .buildStream());
  }

  @Test
  @DisplayName("When instance stream with null arguments expect IllegalArgumentException")
  public void when_instance_stream_with_null_arguments_expect_illegal_argument_exception() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new WordLogger(null, null));
  }
}
