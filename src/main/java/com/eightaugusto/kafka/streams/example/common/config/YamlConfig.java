package com.eightaugusto.kafka.streams.example.common.config;

import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.env.EnvScalarConstructor;
import org.yaml.snakeyaml.representer.Representer;

/** YamlConfig. */
@UtilityClass
public final class YamlConfig {

  private static Yaml YAML;

  /**
   * Get a Yaml custom instance with the EnvConstructor.
   *
   * @return Singleton Yaml.
   */
  public static Yaml getYaml() {
    if (YAML == null) {
      final LoaderOptions loaderOptions = new LoaderOptions();
      loaderOptions.setAllowDuplicateKeys(false);
      final DumperOptions dumperOptions = new DumperOptions();
      final Representer representer = new Representer(dumperOptions);
      representer.getPropertyUtils().setSkipMissingProperties(true);
      YAML = new Yaml(new EnvScalarConstructor(), representer, dumperOptions, loaderOptions);
    }
    return YAML;
  }
}
