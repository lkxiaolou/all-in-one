package org.newboo.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.newboo.share.GlobalExecutor;

import java.util.HashMap;
import java.util.Map;

public class TransmittableThreadLocalDemo2 {

    public static void main(String[] args) throws Exception {

        Map<String, String> map1 = new HashMap<>();
        map1.put("s1", "111");
        TransmittableThreadLocal<Map<String, String>> context = new TransmittableThreadLocal<>();
        // holders.1.size = 1
        context.set(map1);

        Runnable runnable = () -> {

            System.out.println(Thread.currentThread().getName() + "[1]=" + context.get());

            Runnable r = () -> System.out.println(Thread.currentThread().getName() + "[1]=" + context.get());

            Runnable ttlr = TtlRunnable.get(r);

            Map<String, String> map2 = new HashMap<>();
            map2.put("s2", "222");

            // 在 TtlRunnable.get(r) 之后set进去
            // holders.2.size = 2
            TransmittableThreadLocal<Map<String, String>> context2 = new TransmittableThreadLocal<>();
            context2.set(map2);

            Runnable r2 = () -> System.out.println(Thread.currentThread().getName() + "[2]=" + context2.get());

            // holders.2.size = 2
            Runnable ttlr2 = TtlRunnable.get(r2);

            GlobalExecutor.getExecutor().execute(ttlr);
            GlobalExecutor.getExecutor().execute(ttlr2);
        };

        GlobalExecutor.getExecutor().execute(TtlRunnable.get(runnable));
    }
}
