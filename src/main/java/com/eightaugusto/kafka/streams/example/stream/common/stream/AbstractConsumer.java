package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.function.Consumer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

/**
 * AbstractConsumer.
 *
 * @param <K> Key class.
 * @param <V> Value class.
 */
public abstract class AbstractConsumer<K, V> extends AbstractStream
    implements Consumer<KStream<K, V>> {

  /**
   * AbstractConsumer constructor.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties StreamProperties.
   */
  public AbstractConsumer(StreamsBuilder streamsBuilder, StreamProperties streamProperties) {
    super(streamsBuilder, streamProperties);
  }

  /** Abstract function for stream build trigger. */
  @Override
  public void buildStream() {
    accept(
        getStreamsBuilder().stream(getFirstInput(), Consumed.with(getKeySerde(), getValueSerde())));
  }

  /**
   * Key serde.
   *
   * @return Key serde.
   */
  public abstract Serde<K> getKeySerde();

  /**
   * Value serde.
   *
   * @return Value serde.
   */
  public abstract Serde<V> getValueSerde();
}
