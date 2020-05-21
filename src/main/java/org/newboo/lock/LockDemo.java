package org.newboo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class LockDemo {

    public static void main(String[] args) {

        /**
         *  1.可重入锁
          */
        ReentrantLock reentrantLock = new ReentrantLock();

        // 立马返回
        if (reentrantLock.tryLock()) {
            reentrantLock.unlock();
        }

        try {
            // 等待一段时间返回
            if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                reentrantLock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 阻塞获取锁
        reentrantLock.lock();
        try {
            // biz code
        } finally {
            reentrantLock.unlock();
        }

        /**
         * 2.读写锁(共享锁)
         * AQS.state 高16位为为读数量，低16位为写数量
         */
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        // 读/读 可共享
        reentrantReadWriteLock.readLock().lock();
        try {
            // read biz code
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        // 读/写 写/写 不可共享
        reentrantReadWriteLock.writeLock().lock();
        try {
            // write biz code
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

        /**
         * 3.更高吞吐量的读写锁
         */
        StampedLock stampedLock = new StampedLock();
        long stamp = stampedLock.writeLock();
        try {
            // write biz code
        } finally {
            stampedLock.unlock(stamp);
        }

        // 获取一个乐观读锁
        long stamp2 = stampedLock.tryOptimisticRead();
        // 读取数据 ...
        if (stampedLock.validate(stamp2)) {
            // 读取的数据有效
        } else {
            // 数据已经被修改，需要重新读取
        }

        /**
         * 4. synchronized
         * 锁升级: 无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁
         */
        synchronized (LockDemo.class) {
            // biz code
        }
    }

}
