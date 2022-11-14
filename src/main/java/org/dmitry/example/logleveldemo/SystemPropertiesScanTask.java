package org.dmitry.example.logleveldemo;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemPropertiesScanTask extends Properties {
  private static final Logger log = LoggerFactory.getLogger(SystemPropertiesScanTask.class);
  private String propertyFile;

  // @Scheduled(fixedDelay = 1000)
  void loadProperties() {
    try {
      load(new FileReader(propertyFile));
      log.debug("Loaded properties file with {} properties", entrySet().size());
      for (var entries: entrySet()) {
        System.setProperty((String) entries.getKey(), (String) entries.getValue());
      }
    } catch (Exception exception) {
      log.warn("Failed to load properties from file: {}", propertyFile);
    }
  }
}
