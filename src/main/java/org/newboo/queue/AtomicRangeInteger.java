package org.newboo.queue;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicRangeInteger extends Number {

    private AtomicIntegerArray values;
    private int startValue;
    private int endValue;
    private static final int VALUE_OFFSET = 15;

    public AtomicRangeInteger(int startValue, int maxValue) {
        this.values = new AtomicIntegerArray(31);
        this.values.set(VALUE_OFFSET, startValue);
        this.startValue = startValue;
        this.endValue = maxValue - 1;
    }

    public final int incrementAndGet() {
        int next;
        do {
            next = this.values.incrementAndGet(VALUE_OFFSET);
            if (next > endValue && this.values.compareAndSet(VALUE_OFFSET, next, startValue)) {
                return startValue;
            }
        } while (next > endValue);

        return next;
    }

    public final int get() {
        return this.values.get(VALUE_OFFSET);
    }

    @Override
    public int intValue() {
        return this.values.get(VALUE_OFFSET);
    }

    @Override
    public long longValue() {
        return this.values.get(VALUE_OFFSET);
    }

    @Override
    public float floatValue() {
        return this.values.get(VALUE_OFFSET);
    }

    @Override
    public double doubleValue() {
        return this.values.get(VALUE_OFFSET);
    }
}
