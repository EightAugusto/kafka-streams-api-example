package com.eightaugusto.kafka.streams.example.common.serde.jackson;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * JacksonSerde.
 *
 * @param <T> Generic class.
 */
public final class JacksonSerde<T> implements Serde<T> {

  private final Serializer<T> serializer;
  private final Deserializer<T> deserializer;

  private JacksonSerde(Class<T> clazz) {
    this.deserializer = new JacksonDeserializer<>(clazz);
    this.serializer = new JacksonSerializer<>();
  }

  @Override
  public void configure(Map<String, ?> settings, boolean isKey) {
    this.serializer.configure(settings, isKey);
    this.deserializer.configure(settings, isKey);
  }

  @Override
  public void close() {
    this.deserializer.close();
    this.serializer.close();
  }

  @Override
  public Serializer<T> serializer() {
    return this.serializer;
  }

  @Override
  public Deserializer<T> deserializer() {
    return this.deserializer;
  }

  /**
   * Build a Jackson Serde based on a Class.
   *
   * @param clazz Class.
   * @param <T> Class type
   * @return Serde of a class. .
   */
  public static <T> JacksonSerde<T> of(Class<T> clazz) {
    return new JacksonSerde<>(clazz);
  }
}
