package com.diguage.hydra.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author diguage
 * @since 2017-06-21
 */
public class LongAddEventHandler implements EventHandler<LongEvent> {

  @Override
  public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
    long oldValue = event.getValue();
    event.setValue(oldValue + 10000);
    System.out.printf(
        "sequence: %5d，Event: %5d --> %d (add)\n", sequence, oldValue, event.getValue());
  }
}
