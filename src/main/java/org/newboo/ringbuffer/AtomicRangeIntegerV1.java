package org.newboo.ringbuffer;

public class AtomicRangeIntegerV1 extends AtomicRangeInteger {

    public AtomicRangeIntegerV1(int startValue, int endValue) {
        super(startValue, endValue);
    }

    @Override
    public final int incrementAndGet() {

        int cur, next;
        do {
            cur = value.get();
            next = cur + 1 > endValue ? startValue : cur + 1;
            if (value.compareAndSet(cur, next)) {
                return next;
            }
        } while (true);
    }
}
