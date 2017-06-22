package com.diguage.hydra.disruptor;

import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;

/**
 * @author diguage
 * @since 2017-06-21
 */
public class LongEventProcessor implements EventProcessor {
  private RingBuffer<LongEvent> ringBuffer;
  private SequenceBarrier sequenceBarrier;

  public LongEventProcessor(RingBuffer<LongEvent> ringBuffer, SequenceBarrier sequenceBarrier) {
    this.ringBuffer = ringBuffer;
    this.sequenceBarrier = sequenceBarrier;
  }

  @Override
  public Sequence getSequence() {
    return null;
  }

  @Override
  public void halt() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void run() {

  }
}
