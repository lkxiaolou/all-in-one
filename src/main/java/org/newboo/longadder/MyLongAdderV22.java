package org.newboo.longadder;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MyLongAdderV22 {

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


    public MyLongAdderV22(int coreSize) {
        this.coreSize = coreSize;

        this.counts = new AtomicLong[coreSize];

        for (int i = 0; i < coreSize; i++) {
            this.counts[i] = new AtomicLong();
        }
    }

    public void increment() {

        // 获取Thread的随机数
        int h = getProbe();

        int index = getProbe() & (coreSize - 1);
        long r;
        if (!counts[index].compareAndSet(r = counts[index].get(), r + 1)) {
            if (h == 0) {
                // 为0时强制初始化
                ThreadLocalRandom.current();
                h = getProbe();
            }
            // 重新计算随机数
            advanceProbe(h);
            // 重试一次
            if (!counts[index].compareAndSet(r = counts[index].get(), r + 1)) {
                // 用incrementAndGet保底
                counts[index].incrementAndGet();
            }
        }
    }

}
