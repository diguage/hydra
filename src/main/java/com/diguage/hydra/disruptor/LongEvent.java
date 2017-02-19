package com.diguage.hydra.disruptor;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEvent {
  private long value;

  public void setValue(long value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "LongEvent-" + value;
  }
}
