package com.diguage.hydra.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
  public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
    System.out.println("Event: " + event);
  }
}
