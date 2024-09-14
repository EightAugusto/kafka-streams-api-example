package com.eightaugusto.kafka.streams.example.stream.word.mapper;

import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** WordMapperTest. */
public final class WordMapperTest {

  @Test
  @DisplayName("When map String to WordDto expect mapping")
  public void when_map_string_to_word_dto_expect_mapping() {
    final String entity = UUID.randomUUID().toString();
    final WordDto dto = WordMapper.MAPPER.convert(entity);

    Assertions.assertNotNull(dto);
    Assertions.assertEquals(entity, dto.getWord());
  }

  @Test
  @DisplayName("When map null String to WordDto expect null mapping")
  public void when_map_null_string_to_word_dto_expect_null_mapping() {
    Assertions.assertNull(WordMapper.MAPPER.convert(null));
  }
}
