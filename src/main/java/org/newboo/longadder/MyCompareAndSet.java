package org.newboo.longadder;

import java.util.concurrent.atomic.AtomicLong;

public class MyCompareAndSet {

    private final int coreSize = 4;
    private final AtomicLong[] counts;

    public MyCompareAndSet() {
        this.counts = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
        }
    }

    public void compareAndSet() {
        int index = (int) (Thread.currentThread().getId() & (coreSize - 1));
        long r = counts[index].get();
        counts[index].compareAndSet(r, r + 1);
    }

}
