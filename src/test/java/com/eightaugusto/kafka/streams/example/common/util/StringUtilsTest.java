package com.eightaugusto.kafka.streams.example.common.util;

import com.eightaugusto.kafka.streams.example.common.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class StringUtilsTest {

  @Test
  @DisplayName("When test length expect")
  public void when_test_length_expect() {
    Assertions.assertEquals(0, StringUtils.length(null));
    Assertions.assertEquals(0, StringUtils.length(""));
    Assertions.assertEquals(1, StringUtils.length(" "));
  }

  @Test
  @DisplayName("When test isNotBlank expect")
  public void when_test_is_not_blank_expect() {
    Assertions.assertFalse(StringUtils.isNotBlank(null));
    Assertions.assertFalse(StringUtils.isNotBlank(""));
    Assertions.assertFalse(StringUtils.isNotBlank(" "));
    Assertions.assertTrue(StringUtils.isNotBlank("test"));
    Assertions.assertTrue(StringUtils.isNotBlank("  test  "));
  }

  @Test
  @DisplayName("When test isBlank expect")
  public void when_test_is_blank_expect() {
    Assertions.assertTrue(StringUtils.isBlank(null));
    Assertions.assertTrue(StringUtils.isBlank(""));
    Assertions.assertTrue(StringUtils.isBlank(" "));
    Assertions.assertFalse(StringUtils.isBlank("test"));
    Assertions.assertFalse(StringUtils.isBlank("  test  "));
  }
}
