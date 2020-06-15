package org.newboo.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockException {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        int i = ThreadLocalRandom.current().nextInt(2);

        try {
            if (i == 1) {
                // 这个异常可能会被 lock.unlock() 异常覆盖
                throw new RuntimeException("xxx");
            }
            // 所以lock一定要在try外面
            lock.lock();
        } finally {
            lock.unlock();
        }
    }
}
