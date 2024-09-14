package com.eightaugusto.kafka.streams.example.common.serde.jackson;

import com.eightaugusto.kafka.streams.example.common.config.ObjectMapperConfig;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** JacksonSerializerTest. */
public final class JacksonSerializerTest {

  private static final JacksonSerializer<WordDto> JACKSON_SERIALIZER = new JacksonSerializer<>();
  private static final String JACKSON_SERIALIZER_TOPIC = UUID.randomUUID().toString();

  @Test
  @DisplayName("When serialize dto expect value")
  public void when_deserialize_json_expect_value() throws JsonProcessingException {
    final String entity = UUID.randomUUID().toString();
    final WordDto dto = new WordDto();
    dto.setWord(entity);
    final String json = new String(JACKSON_SERIALIZER.serialize(JACKSON_SERIALIZER_TOPIC, dto));

    Assertions.assertNotNull(json);
    Assertions.assertEquals(ObjectMapperConfig.getObjectMapper().writeValueAsString(dto), json);
  }

  @Test
  @DisplayName("When serialize null value expect null")
  public void when_serialize_null_value_expect_null() {
    Assertions.assertNull(JACKSON_SERIALIZER.serialize(JACKSON_SERIALIZER_TOPIC, null));
  }
}
