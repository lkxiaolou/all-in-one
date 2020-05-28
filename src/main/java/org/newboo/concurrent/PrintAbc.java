package org.newboo.concurrent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbc {

    private static Lock lockA = new ReentrantLock();
    private static Condition conditionA = lockA.newCondition();

    private static Condition conditionB = lockA.newCondition();

    private static int count = 0;

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (true) {

                lockA.lock();
                try {
                    if (count % 2 != 0) {
                        conditionB.signal();
                        conditionA.await();
                    } else {
                        System.out.println("A");
                        Thread.sleep(1000);
                        count++;
                        conditionB.signal();
                    }
                    conditionA.signal();
                } catch (Exception e) {

                }
                finally {
                    lockA.unlock();
                }
            }
        });

        Thread b = new Thread(() -> {
            while (true) {
                lockA.lock();
                try {
                    if (count % 2 != 1) {
                        conditionA.signal();
                        conditionB.await();
                    } else {
                        System.out.println("B");
                        Thread.sleep(1000);
                        count++;
                        conditionA.signal();
                    }
                    conditionB.signal();
                } catch (Exception e) {

                }
                finally {
                    lockA.unlock();
                }
            }
        });
        a.start();
        b.start();
    }

}
