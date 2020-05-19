package org.newboo.longadder;

import sun.misc.Contended;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV4 {

    private static class AtomicLongWrap {
        @Contended
        private final AtomicLong value = new AtomicLong();
    }

    private final int coreSize;
    private final AtomicLongWrap[] counts;

    public MyLongAdderV4(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLongWrap[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLongWrap();
        }
    }

    public void increment() {
        counts[ThreadLocalRandom.current().nextInt(coreSize)].value.incrementAndGet();
    }

}
