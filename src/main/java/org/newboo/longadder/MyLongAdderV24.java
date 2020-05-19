package org.newboo.longadder;

import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV24 {

    private final int coreSize;

    private final AtomicLong[] counts1;
    private final AtomicLong[] counts2;
    private final AtomicLong[] counts3;

    public MyLongAdderV24(int coreSize) {
        this.coreSize = coreSize;

        this.counts1 = new AtomicLong[coreSize];
        this.counts2 = new AtomicLong[coreSize];
        this.counts3 = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts1[i] = new AtomicLong();
            this.counts2[i] = new AtomicLong();
            this.counts3[i] = new AtomicLong();
        }
    }

    public void increment() {

        int index = (int) (Thread.currentThread().getId() & (coreSize - 1));
        long r = this.counts1[index].get();
        if (!this.counts1[index].compareAndSet(r, r + 1)) {
            r = this.counts2[index].get();
            if (!this.counts2[index].compareAndSet(r, r + 1)) {
                this.counts3[index].incrementAndGet();
            }
        }
    }

}
