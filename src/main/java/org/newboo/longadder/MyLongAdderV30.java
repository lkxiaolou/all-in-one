package org.newboo.longadder;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLongArray;

public class MyLongAdderV30 {

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

    @Contended
    private final int coreSize = 4;

    private static final int offset = 8;

    private final AtomicLongArray[] counts;

    public MyLongAdderV30(int coreSize) {

        this.counts = new AtomicLongArray[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLongArray(15);
        }
    }

    public void increment() {

        int index = ThreadLocalRandom.current().nextInt(coreSize);
        long r;
        if (!counts[index].compareAndSet(offset, r = counts[index].get(offset), r + 1)) {

            if (!counts[index].compareAndSet(offset, r = counts[index].get(offset), r + 1)) {
                counts[index].incrementAndGet(offset);
            }
        }
    }

}
