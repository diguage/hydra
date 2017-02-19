package com.diguage.hydra.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
  public LongEvent newInstance() {
    return new LongEvent();
  }
}
