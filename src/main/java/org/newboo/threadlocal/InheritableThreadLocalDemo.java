package org.newboo.threadlocal;

import org.newboo.share.GlobalExecutor;

import java.util.ArrayList;
import java.util.List;

public class InheritableThreadLocalDemo {

    public static void main(String[] args) throws Exception {
        ThreadLocal<List<String>> t1 = new ThreadLocal<>();
        ThreadLocal<List<String>> t2 = new InheritableThreadLocal<>();

        List<String> l1 = new ArrayList<>();
        l1.add("111");

        List<String> l2 = new ArrayList<>();
        l2.add("222");

        t1.set(l1);
        t2.set(l2);

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + t1.get());
            System.out.println(Thread.currentThread().getName() + " " + t2.get());
            // 测试引用传递
            t2.get().add("333");
        });
        thread.start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " " + t2.get());

        // 测试线程池传递
        for (int i = 0; i < 4; i++) {
            GlobalExecutor.getExecutor().execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + t2.get());
            });
        }
        Thread.sleep(1000);

        List<String> l3 = new ArrayList<>();
        l3.add("444");
        t2.set(l3);
        for (int i = 0; i < 4; i++) {
            GlobalExecutor.getExecutor().execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + t2.get());
            });
        }
        Thread.sleep(1000);
    }

}
