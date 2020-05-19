package org.newboo.longadder;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV5 {

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

    private static class AtomicLongWrap {
        @Contended
        private final AtomicLong value = new AtomicLong();
    }

    private final int coreSize;
    private final AtomicLongWrap[] counts;

    public MyLongAdderV5(int coreSize) {
        this.coreSize = coreSize;
        this.counts = new AtomicLongWrap[coreSize];
        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLongWrap();
        }
    }

    public void increment() {

        int h = getProbe();

        int index = getProbe() & (coreSize - 1);
        long r;
        if (!counts[index].value.compareAndSet(r = counts[index].value.get(), r + 1)) {
            if (h == 0) {
                // 初始化随机数
                ThreadLocalRandom.current();
                h = getProbe();
            }
            // 冲突后重新生成随机数
            advanceProbe(h);
            // 用getAndIncrement来兜底
            counts[index].value.getAndIncrement();

        }
    }

}
