package org.newboo.longadder;

import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV31 {

    private static ThreadLocal<Integer> local = ThreadLocal.withInitial(
            () -> 0);

    private final int coreSize;
    private final AtomicLong[] counts;

    public MyLongAdderV31(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
        }
    }

    public void increment() {
        int index = 0;
        long r = counts[index].get();

        while (!counts[index].compareAndSet(r, r + 1)) {
            local.set(local.get() + 1);
            index = local.get() & (coreSize -1);
            r = counts[index].get();
        }
    }

}
