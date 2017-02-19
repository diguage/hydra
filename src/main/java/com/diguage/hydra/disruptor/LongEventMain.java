package com.diguage.hydra.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author diguage
 * @since 14/11/2016.
 */
public class LongEventMain {
  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newCachedThreadPool();
    LongEventFactory eventFactory = new LongEventFactory();
    int bufferSize = 1024;
    Disruptor<LongEvent> disruptor =
        new Disruptor<LongEvent>(eventFactory, bufferSize, executorService);
    disruptor.handleEventsWith(new LongEventHandler());
    disruptor.start();
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

    LongEventProducer producer = new LongEventProducer(ringBuffer);
    ByteBuffer byteBuffer = ByteBuffer.allocate(8);
    for (long i = 0; true; i++) {
      byteBuffer.putLong(0, i);
      producer.onData(byteBuffer);
      Thread.sleep(500);
    }
  }
}
