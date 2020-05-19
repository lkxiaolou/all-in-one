package org.newboo.longadder;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV23 {

    private static sun.misc.Unsafe UNSAFE = null;
    private static final long PROBE;
    static {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
        } catch (Exception e) {

        }

        try {
            Class<?> tk = Thread.class;
            PROBE = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomProbe"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }

    private final int coreSize;

    private final AtomicLong[] counts;
    private final AtomicLong[] counts2;
    private final AtomicLong[] counts3;

    public MyLongAdderV23(int coreSize) {
        this.coreSize = coreSize;

        this.counts = new AtomicLong[coreSize];
        this.counts2 = new AtomicLong[coreSize];
        this.counts3 = new AtomicLong[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
            this.counts2[i] = new AtomicLong();
            this.counts3[i] = new AtomicLong();
        }
    }

    public void increment() {

        int h = getProbe();

        int index = getProbe() & (coreSize - 1);
        long r;
        if (!counts[index].compareAndSet(r = counts[index].get(), r + 1)) {
            if (h == 0) {
                ThreadLocalRandom.current();
                h = getProbe();
            }
            advanceProbe(h);
            if (!counts2[index].compareAndSet(r = counts2[index].get(), r + 1)) {
                counts3[index].incrementAndGet();
            }
        }
    }

}
