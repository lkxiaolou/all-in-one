package org.newboo.longadder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV21 {

    private final int coreSize;

    private final AtomicLong[] counts;

    public MyLongAdderV21(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
        }
    }

    public void increment() {
        counts[ThreadLocalRandom.current().nextInt(coreSize)].incrementAndGet();
    }

}
