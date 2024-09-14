package com.eightaugusto.kafka.streams.example.stream.common.properties;

import java.util.List;
import lombok.Data;

/** StreamProperties. */
@Data
public final class StreamProperties {

  private boolean enabled;
  private List<String> input;
  private List<String> output;
}
