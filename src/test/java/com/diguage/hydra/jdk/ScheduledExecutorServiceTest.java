package com.diguage.hydra.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author diguage
 * @since 2017-05-23
 */
public class ScheduledExecutorServiceTest {
  private static final Logger logger = LoggerFactory.getLogger(ScheduledExecutorServiceTest.class);

  @Test
  public void test() {
    logger.info("start to test");
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    executorService.scheduleWithFixedDelay(
        () -> {
          logger.error("throw Exception");
          try {
            throw new RuntimeException("There is a error!");
          } catch (Throwable e) {

          }
        },
        5,
        10,
        TimeUnit.SECONDS);
    logger.info("finish submiting task");
    LockSupport.park(this);
  }
}
