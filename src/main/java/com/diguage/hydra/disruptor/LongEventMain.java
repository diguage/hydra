package com.diguage.hydra.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEventMain {
  public static void main(String[] args) throws Exception {
    LongEventFactory eventFactory = new LongEventFactory();
    int bufferSize = 64;
    Disruptor<LongEvent> disruptor =
        new Disruptor<>(eventFactory, bufferSize, DaemonThreadFactory.INSTANCE);

//    testSingleEventHandler(disruptor);
    testSingleEventHandlerChain(disruptor);

    disruptor.start();

    productData(disruptor);
  }

  private static void productData(Disruptor<LongEvent> disruptor) throws InterruptedException {
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
    LongEventProducer producer = new LongEventProducer(ringBuffer);
    ByteBuffer byteBuffer = ByteBuffer.allocate(8);
    for (long i = 0; true; i++) {
      byteBuffer.putLong(0, i);
      producer.onData(byteBuffer);
      Thread.sleep(500);
    }
  }

  private static void testSingleEventHandler(Disruptor<LongEvent> disruptor) {
    disruptor.handleEventsWith(new LongPrintEventHandler());
  }

  private static void testSingleEventHandlerChain(Disruptor<LongEvent> disruptor) {
    disruptor
        .handleEventsWith(new LongPrintEventHandler())
        .then(new LongAddEventHandler())
        .then(new LongPrintEventHandler());
  }
}
