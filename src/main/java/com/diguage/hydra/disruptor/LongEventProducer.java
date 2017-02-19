package com.diguage.hydra.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEventProducer {
  private final RingBuffer<LongEvent> ringBuffer;

  public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
    this.ringBuffer = ringBuffer;
  }

  public void onData(ByteBuffer bb) {
    long sequence = ringBuffer.next();
    try {
      LongEvent event = ringBuffer.get(sequence);
      event.setValue(bb.getLong(0));
    } finally {
      ringBuffer.publish(sequence);
    }
  }
}
