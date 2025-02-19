package com.eightaugusto.kafka.streams.example.stream.wordsplitter.stream;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamPropertiesFactory;
import com.eightaugusto.kafka.streams.example.stream.common.stream.AbstractProducerTest;
import com.eightaugusto.kafka.streams.example.stream.word.dto.WordDto;
import java.util.List;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** WordSplitterTest. */
public class WordSplitterTest extends AbstractProducerTest<String, String, String, WordDto> {

  private static final String HELLO_WORLD = "Hello World";
  private static final String[] HELLO_WORLD_ARRAY = new String[] {"Hello", "World"};

  public WordSplitterTest() {
    super(StreamPropertiesFactory.getStreamProperties(1, 1), WordSplitter::new);
  }

  @Test
  @DisplayName("When send input record expect split output record")
  public void when_send_input_record_expect_split_output_record() {
    getInputTopic().pipeInput(HELLO_WORLD);

    final List<WordDto> records = getOutputTopic().readValuesToList();

    Assertions.assertEquals(HELLO_WORLD_ARRAY.length, records.size());
    for (int i = 0; i < HELLO_WORLD_ARRAY.length; i++) {
      Assertions.assertEquals(HELLO_WORLD_ARRAY[i], records.get(i).getWord());
    }
  }

  @Test
  @DisplayName("When send empty input record expect empty output record")
  public void when_send_empty_input_record_expect_empty_output_record() {
    getInputTopic().pipeInput(new TestRecord<>(null, null));

    Assertions.assertTrue(getOutputTopic().readValuesToList().isEmpty());
  }
}
