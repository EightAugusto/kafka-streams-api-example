package com.eightaugusto.kafka.streams.example.common.serde.jackson;

import com.eightaugusto.kafka.streams.example.common.config.ObjectMapperConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

/**
 * JacksonSerializer.
 *
 * @param <T> Generic class.
 */
public class JacksonSerializer<T> implements Serializer<T> {

  @Override
  public byte[] serialize(String topic, T value) {
    if (value == null) {
      return null;
    }
    try {
      return ObjectMapperConfig.getObjectMapper().writeValueAsBytes(value);
    } catch (JsonProcessingException ex) {
      throw new SerializationException(ex);
    }
  }
}
