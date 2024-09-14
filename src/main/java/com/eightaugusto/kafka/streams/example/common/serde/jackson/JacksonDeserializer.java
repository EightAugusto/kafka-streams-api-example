package com.eightaugusto.kafka.streams.example.common.serde.jackson;

import com.eightaugusto.kafka.streams.example.common.config.ObjectMapperConfig;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * JacksonDeserializer.
 *
 * @param <T> Generic class.
 */
@AllArgsConstructor
public final class JacksonDeserializer<T> implements Deserializer<T> {

  private final Class<T> clazz;

  @Override
  public T deserialize(String topic, byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    try {
      return ObjectMapperConfig.getObjectMapper().readValue(bytes, clazz);
    } catch (IOException ex) {
      throw new SerializationException(ex);
    }
  }
}
