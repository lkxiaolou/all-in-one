package org.newboo.ringbuffer;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicRangeIntegerV2 extends Number {

    protected final AtomicInteger value;

    protected final int startValue;
    protected final int endValue;

    public AtomicRangeIntegerV2(int startValue, int endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.value = new AtomicInteger(startValue);
    }

    public int incrementAndGet() {
        int next;
        do {
            next = value.incrementAndGet();
            if (next > endValue && value.compareAndSet(next, startValue)) {
                return startValue;
            }
        } while (next > endValue);

        return next;
    }

    public int get() {
        return value.intValue();
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.intValue();
    }

    @Override
    public float floatValue() {
        return value.intValue();
    }

    @Override
    public double doubleValue() {
        return value.intValue();
    }
}
