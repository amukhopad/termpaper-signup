package ua.edu.ukma.termpapers.config;

import static java.util.Objects.requireNonNull;

import java.net.URL;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HBaseConfig {

  @Bean
  public Configuration hBaseConfiguration(
      @Value("${hbase.config.xml}") final String configResource) {
    Configuration hBaseConfiguration = HBaseConfiguration.create();

    final URL resourceUrl = requireNonNull(getClass().getClassLoader().getResource(configResource),
        "HBase XML configuration file not found.");
    hBaseConfiguration.addResource(resourceUrl);

    return hBaseConfiguration;
  }
}
