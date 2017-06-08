package com.diguage.hydra.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * {@link AbstractQueuedSynchronizer} 使用示例
 *
 * @author diguage
 * @since 2017-02-12
 */
public class SimpleLock extends AbstractQueuedSynchronizer {
  private static final long serialVersionUID = -7316320116933187634L;

  public SimpleLock() {}

  @Override
  protected boolean tryAcquire(int unused) {
    if (compareAndSetState(0, 1)) {
      setExclusiveOwnerThread(Thread.currentThread());
      return true;
    }
    return false;
  }

  @Override
  protected boolean tryRelease(int unused) {
    setExclusiveOwnerThread(null);
    setState(0);
    return true;
  }

  public void lock() {
    acquire(1);
  }

  public boolean tryLock() {
    return tryAcquire(1);
  }

  public void unlock() {
    release(1);
  }

  public boolean isLocked() {
    return isHeldExclusively();
  }

  public static void main(String[] args) throws InterruptedException {
    final SimpleLock lock = new SimpleLock();
    lock.lock();
    final List<Thread> threadList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      Thread thread =
          new Thread(
              new Runnable() {
                @Override
                public void run() {
                  Thread currentThread = Thread.currentThread();
                  lock.lock();

                  while (!currentThread.isInterrupted()) {
                    System.out.println(currentThread.getName() + " acquired the lock!");
                    try {
                      TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  }

                  System.out.println("\n\n" + currentThread.getName() + " was interrupted!\n\n");
                  lock.unlock();
                  System.exit(0);
                }
              });
      threadList.add(thread);
      thread.start();
    }

    System.out.println("main thread unlock!");
    lock.unlock();
    TimeUnit.SECONDS.sleep(2);
    Thread thread = threadList.get(0);
//    thread.interrupt();
  }
}
