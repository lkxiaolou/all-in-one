package org.newboo.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class GlobalExecutor {

    private static final AtomicLong count = new AtomicLong(0);

    private static ExecutorService executor = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new SynchronousQueue<>(), r -> {
        Thread thread = new Thread(r);
        thread.setName("org.newboo.share" + count.incrementAndGet());
        thread.setDaemon(false);
        return thread;
    });

    public static ExecutorService getExecutor() {
        return executor;
    }
}
