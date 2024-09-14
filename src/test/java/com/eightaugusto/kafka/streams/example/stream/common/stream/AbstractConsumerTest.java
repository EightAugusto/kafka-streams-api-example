package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.function.BiFunction;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.TestInputTopic;

/**
 * AbstractConsumerTest.
 *
 * @param <K> Key class.
 * @param <V> Value class.
 */
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractConsumerTest<K, V>
    extends AbstractStreamTest<AbstractConsumer<K, V>> {

  private final TestInputTopic<K, V> inputTopic;

  public AbstractConsumerTest(
      StreamProperties streamProperties,
      BiFunction<StreamsBuilder, StreamProperties, AbstractConsumer<K, V>> streamBiFunction) {
    super(streamProperties, streamBiFunction);

    final AbstractConsumer<K, V> consumer = getStream();

    this.inputTopic =
        getTopology()
            .createInputTopic(
                getFirstSource(),
                consumer.getKeySerde().serializer(),
                consumer.getValueSerde().serializer());
  }
}
