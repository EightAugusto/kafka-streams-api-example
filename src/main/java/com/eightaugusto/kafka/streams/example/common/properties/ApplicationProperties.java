package com.eightaugusto.kafka.streams.example.common.properties;

import com.eightaugusto.kafka.streams.example.stream.common.properties.StreamProperties;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

/** ApplicationProperties. */
@Data
@NoArgsConstructor
public final class ApplicationProperties {

  private KafkaProperties kafka;
  private Map<String, StreamProperties> stream;
}
