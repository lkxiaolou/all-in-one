package org.newboo.queue;

import com.lmax.disruptor.RingBuffer;

public class LogEventProducer {
    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int id) {
        long sequence = ringBuffer.next();
        try {
            LogEvent logEvent = ringBuffer.get(sequence);
            logEvent.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
