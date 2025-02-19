package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.function.Function;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

/**
 * AbstractProducer.
 *
 * @param <K0> Source key class.
 * @param <V0> Source value class.
 * @param <K1> Destination key class.
 * @param <V1> Destination value class.
 */
public abstract class AbstractProducer<K0, V0, K1, V1> extends AbstractStream
    implements Function<KStream<K0, V0>, KStream<K1, V1>> {

  /**
   * AbstractProducer constructor.
   *
   * @param streamsBuilder StreamsBuilder.
   * @param streamProperties StreamProperties.
   */
  public AbstractProducer(StreamsBuilder streamsBuilder, StreamProperties streamProperties) {
    super(streamsBuilder, streamProperties);
  }

  /** Abstract function for stream build trigger. */
  @Override
  public void buildStream() {
    this.apply(
            getStreamsBuilder().stream(
                getFirstInput(), Consumed.with(getSourceKeySerde(), getSourceValueSerde())))
        .to(getFirstOutput(), Produced.with(getDestinationKeySerde(), getDestinationValueSerde()));
  }

  /**
   * Key source serde.
   *
   * @return Key source serde.
   */
  protected abstract Serde<K0> getSourceKeySerde();

  /**
   * Value source serde.
   *
   * @return Value source serde.
   */
  protected abstract Serde<V0> getSourceValueSerde();

  /**
   * Key destination serde.
   *
   * @return Key destination serde.
   */
  protected abstract Serde<K1> getDestinationKeySerde();

  /**
   * Value destination serde.
   *
   * @return Value destination serde.
   */
  protected abstract Serde<V1> getDestinationValueSerde();
}
