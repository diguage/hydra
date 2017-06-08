package com.diguage.hydra.jdk;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author diguage
 * @since 2017-02-19
 */
public class TwinsLockMainTest {
  // 使用 Main 方法，排除不必要的干扰。
  public static void main(String[] args) {
    final Lock lock = new TwinsLock();
    class Worker extends Thread {
      @Override
      public void run() {
        while (true) {
          lock.lock();
          Thread thread = Thread.currentThread();
          System.out.println(thread.getName() + " -- " + thread.getId());
          try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            lock.unlock();
          }
        }
      }
    }

    for (int i = 0; i < 10; i++) {
      Worker worker = new Worker();
      worker.setDaemon(true);
      worker.start();
    }

    for (int i = 0; i < 600; i++) {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println();
    }
  }
}
