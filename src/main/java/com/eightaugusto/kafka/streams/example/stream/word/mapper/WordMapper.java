package com.eightaugusto.kafka.streams.example.stream.word.mapper;

import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import org.mapstruct.Mapper;

/** WordMapper. */
@Mapper
public interface WordMapper {

  WordMapper MAPPER = new WordMapperImpl();

  /**
   * Convert Word String to Word Dto.
   *
   * @param word Word String.
   * @return Word Dto.
   */
  WordDto convert(String word);
}
