package com.eightaugusto.kafka.streams.example.common.config;

import com.eightaugusto.kafka.streams.example.common.properties.ApplicationProperties;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import lombok.experimental.UtilityClass;

/** ApplicationConfig. */
@UtilityClass
public final class ApplicationConfig {

  private static final String DEFAULT_APPLICATION_YML_FILE_NAME = "application.yml";

  private static WeakReference<ApplicationProperties> APPLICATION_PROPERTIES;

  /**
   * Get ApplicationProperties from a WeakReference.
   *
   * @return ApplicationProperties.
   */
  public static ApplicationProperties getApplicationProperties() {
    if (APPLICATION_PROPERTIES == null || APPLICATION_PROPERTIES.get() == null) {
      APPLICATION_PROPERTIES =
          new WeakReference<>(
              YamlConfig.getYaml()
                  .loadAs(getDefaultApplicationInputStream(), ApplicationProperties.class));
    }
    return APPLICATION_PROPERTIES.get();
  }

  /**
   * Get InputStream from default application file.
   *
   * @return InputStream.
   */
  private static InputStream getDefaultApplicationInputStream() {
    return ApplicationProperties.class
        .getClassLoader()
        .getResourceAsStream(DEFAULT_APPLICATION_YML_FILE_NAME);
  }
}
