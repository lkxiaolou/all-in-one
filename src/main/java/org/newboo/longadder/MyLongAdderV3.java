package org.newboo.longadder;

import sun.misc.Contended;

import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV3 {

    private static class AtomicLongWrap {
        @Contended
        private final AtomicLong value = new AtomicLong();
    }

    private final int coreSize;
    private final AtomicLongWrap[] counts;

    public MyLongAdderV3(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLongWrap[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLongWrap();
        }
    }

    public void increment() {
        int index = Thread.currentThread().hashCode() & (coreSize - 1);
        counts[index].value.incrementAndGet();
    }

}
