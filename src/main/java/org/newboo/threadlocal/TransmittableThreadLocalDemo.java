package org.newboo.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.newboo.share.GlobalExecutor;

import java.util.HashMap;
import java.util.Map;

public class TransmittableThreadLocalDemo {

    public static void main(String[] args) throws Exception {

        ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();
        Map<String, String> t = new HashMap<>();
        t.put("t1", "OK");
        threadLocal.set(t);

        // 测试已经被污染的context再次执行新任务时是否会刷新
        Map<String, String> map = new HashMap<>();
        map.put("s1", "111");

        TransmittableThreadLocal<Map<String, String>> context = new TransmittableThreadLocal<>();
        context.set(map);

        System.out.println(Thread.currentThread().getName() + " set context = " + context.get());
        System.out.println(Thread.currentThread().getName() + " threadLocal = " + threadLocal.get());

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " context = " + context.get());
            System.out.println(Thread.currentThread().getName() + " threadLocal = " + threadLocal.get());
        };

        for (int i = 0; i < 4; i++) {
            // TransmittableThreadLocal继承InheritableThreadLocal
            GlobalExecutor.getExecutor().execute(runnable);
        }

        Thread.sleep(1000L);

        Map<String, String> map2 = new HashMap<>();
        map2.put("s2", "222");

        // @1
        context.set(map2);

        // @2 将threadLocal也注册进去
        TransmittableThreadLocal.Transmitter.registerThreadLocal(threadLocal, context);

        System.out.println(Thread.currentThread().getName() + " set context = " + context.get());

        // 这句一定要在 @1 @2 之后执行
        Runnable ttlRunnable = TtlRunnable.get(runnable);

        for (int i = 0; i < 4; i++) {
            GlobalExecutor.getExecutor().execute(ttlRunnable);
        }
        Thread.sleep(1000L);
    }
}
