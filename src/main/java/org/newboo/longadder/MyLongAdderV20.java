package org.newboo.longadder;

import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV20 {

    private final int coreSize;

    private final AtomicLong[] counts;

    public MyLongAdderV20(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
        }
    }

    public void increment() {
        int index = Thread.currentThread().hashCode() & (coreSize - 1);
        counts[index].incrementAndGet();
    }

}
