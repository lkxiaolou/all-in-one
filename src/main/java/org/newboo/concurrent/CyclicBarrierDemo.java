package org.newboo.concurrent;


import org.newboo.share.GlobalExecutor;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception {

        int count = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        AtomicReference<Thread> one = new AtomicReference<>();
        AtomicBoolean flag = new AtomicBoolean(false);

        for (int i = 0; i < count; i++) {
            GlobalExecutor.getExecutor().execute(() -> {
                if (one.get() == null) {
                    one.set(Thread.currentThread());
                }
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500));
                    System.out.println(Thread.currentThread().getName() + " start");
                    if (flag.compareAndSet(false, true)) {
                        // 如果一个线程超时，其他线程抛出BrokenBarrierException
                        cyclicBarrier.await(100L, TimeUnit.MILLISECONDS);
                    } else {
                        cyclicBarrier.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(200);
        // 如果某线程被打断，则其他线程都会抛出BrokenBarrierException
        //one.get().interrupt();
        // 有线程进入await状态时调用reset会抛出BrokenBarrierException
        //cyclicBarrier.reset();
    }

}
