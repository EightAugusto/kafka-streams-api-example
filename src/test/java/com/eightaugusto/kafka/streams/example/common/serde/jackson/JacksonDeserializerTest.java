package com.eightaugusto.kafka.streams.example.common.serde.jackson;

import com.eightaugusto.kafka.streams.example.common.config.ObjectMapperConfig;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** JacksonDeserializerTest. */
public final class JacksonDeserializerTest {

  private static final JacksonDeserializer<WordDto> JACKSON_DESERIALIZER =
      new JacksonDeserializer<>(WordDto.class);
  private static final String JACKSON_DESERIALIZER_TOPIC = UUID.randomUUID().toString();

  @Test
  @DisplayName("When deserialize json expect value")
  public void when_deserialize_json_expect_value() throws JsonProcessingException {
    final String entity = UUID.randomUUID().toString();
    WordDto dto = new WordDto();
    dto.setWord(entity);
    final String json = ObjectMapperConfig.getObjectMapper().writeValueAsString(dto);

    dto = JACKSON_DESERIALIZER.deserialize(JACKSON_DESERIALIZER_TOPIC, json.getBytes());

    Assertions.assertNotNull(dto);
    Assertions.assertEquals(entity, dto.getWord());
  }

  @Test
  @DisplayName("When deserialize null value expect null")
  public void when_deserialize_null_value_expect_null() {
    Assertions.assertNull(JACKSON_DESERIALIZER.deserialize(JACKSON_DESERIALIZER_TOPIC, null));
  }

  @Test
  @DisplayName("When deserialize invalid value expect SerializationException")
  public void when_deserialize_invalid_value_expect_serialization_exception() {
    Assertions.assertThrows(
        SerializationException.class,
        () -> JACKSON_DESERIALIZER.deserialize(JACKSON_DESERIALIZER_TOPIC, new byte[0]));
  }
}
