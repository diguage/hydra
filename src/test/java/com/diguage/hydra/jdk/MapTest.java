package com.diguage.hydra.jdk;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author diguage
 * @since 2017-05-24
 */
public class MapTest {
  private static final Object EMPTY_OBJECT = new Object();

  @Test
  public void test() {
    ConcurrentMap<Integer, Object> data = new ConcurrentHashMap<>();
    System.out.println(data.putIfAbsent(1, EMPTY_OBJECT) == EMPTY_OBJECT);
    System.out.println(data.putIfAbsent(1, EMPTY_OBJECT) == EMPTY_OBJECT);
  }
}
