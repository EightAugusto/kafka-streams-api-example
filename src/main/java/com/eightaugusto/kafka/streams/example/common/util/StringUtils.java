package com.eightaugusto.kafka.streams.example.common.util;

import lombok.experimental.UtilityClass;

/** StringUtils. */
@UtilityClass
public final class StringUtils {

  /**
   * Get the CharSequence length.
   *
   * @param charSequence CharSequence.
   * @return CharSequence length.
   */
  public static int length(final CharSequence charSequence) {
    return charSequence == null ? 0 : charSequence.length();
  }

  /**
   * Verify if the CharSequence is not blank.
   *
   * @param charSequence CharSequence.
   * @return Boolean is CharSequence is not blank.
   */
  public static boolean isNotBlank(final CharSequence charSequence) {
    return !isBlank(charSequence);
  }

  /**
   * Verify if the CharSequence is blank.
   *
   * @param charSequence CharSequence.
   * @return Boolean is CharSequence is blank.
   */
  public static boolean isBlank(final CharSequence charSequence) {
    final int stringLength = length(charSequence);
    if (stringLength == 0) {
      return true;
    }
    for (int i = 0; i < stringLength; i++) {
      if (!Character.isWhitespace(charSequence.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}
