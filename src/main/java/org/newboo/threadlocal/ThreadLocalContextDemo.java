package org.newboo.threadlocal;

public class ThreadLocalContextDemo {

    public static void main(String[] args) throws Exception {

        ThreadContext.getLocal().get().getMap().put("s1", "111");
        ThreadContext.getInheritableLocal().get().getMap().put("m2", "222");

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " local= " + ThreadContext.getLocal().get());
            System.out.println(Thread.currentThread().getName() + " InheritableLocal= " + ThreadContext.getInheritableLocal().get());
            ThreadContext.getInheritableLocal().get().getMap().put("m3", "333");
            ThreadContext.getLocal().get().getMap().put("s3", "-333");
        });
        thread.start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " local= " + ThreadContext.getLocal().get());
        System.out.println(Thread.currentThread().getName() + " InheritableLocal= " + ThreadContext.getInheritableLocal().get());
    }

}
