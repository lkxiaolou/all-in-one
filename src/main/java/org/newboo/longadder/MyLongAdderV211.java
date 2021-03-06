package org.newboo.longadder;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLongArray;

public class MyLongAdderV211 {

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

    private static final int length = 15;
    private static final int offset = 7;

    private final AtomicLongArray[] counts;

    public MyLongAdderV211(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLongArray[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLongArray(length);
        }
    }

    public void increment() {

        int h = getProbe();

        int index = getProbe() & (coreSize - 1);
        long r;
        if (!counts[index].compareAndSet(offset, r = counts[index].get(offset), r + 1)) {
            if (h == 0) {
                ThreadLocalRandom.current();
                h = getProbe();
            }
            advanceProbe(h);
            if (!counts[index].compareAndSet(offset, r = counts[index].get(offset), r + 1)) {
                counts[index].incrementAndGet(offset);
            }
        }
    }

}
