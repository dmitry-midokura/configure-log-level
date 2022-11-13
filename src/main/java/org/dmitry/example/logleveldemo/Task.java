package org.dmitry.example.logleveldemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

  private static final Logger log = LoggerFactory.getLogger(Task.class);

  @Scheduled(fixedDelay = 1000)
  void run() {
    log.info("Logger level is >= info");
    log.debug("Logger level is >= debug");
  }

}
