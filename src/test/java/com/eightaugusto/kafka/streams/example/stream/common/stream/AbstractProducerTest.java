package com.eightaugusto.kafka.streams.example.stream.common.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.function.BiFunction;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;

/**
 * AbstractProducerTest.
 *
 * @param <K0> Source key class.
 * @param <V0> Source value class.
 * @param <K1> Destination key class.
 * @param <V1> Destination value class.
 */
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractProducerTest<K0, V0, K1, V1>
    extends AbstractStreamTest<AbstractProducer<K0, V0, K1, V1>> {

  private final TestInputTopic<K0, V0> inputTopic;
  private final TestOutputTopic<K1, V1> outputTopic;

  public AbstractProducerTest(
      StreamProperties streamProperties,
      BiFunction<StreamsBuilder, StreamProperties, AbstractProducer<K0, V0, K1, V1>>
          streamBiFunction) {
    super(streamProperties, streamBiFunction);

    final AbstractProducer<K0, V0, K1, V1> producer = getStream();

    this.inputTopic =
        getTopology()
            .createInputTopic(
                getFirstSource(),
                producer.getSourceKeySerde().serializer(),
                producer.getSourceValueSerde().serializer());
    this.outputTopic =
        getTopology()
            .createOutputTopic(
                getFirstDestination(),
                producer.getDestinationKeySerde().deserializer(),
                producer.getDestinationValueSerde().deserializer());
  }
}
