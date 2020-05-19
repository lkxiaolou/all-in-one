package org.newboo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceDemo {

    public static void main(String[] args) {

        // 强引用
        Object obj = new Object();

        // 软引用：内存不够时，对象才会被回收
        Object obj1 = new Object();
        // 对象被回收后，引用被放入 ReferenceQueue
        ReferenceQueue softQueue = new ReferenceQueue();
        SoftReference softReference = new SoftReference(obj1, softQueue);
        obj1 = null;
        System.gc();

        // 弱引用：被垃圾收集器扫描到即回收
        Object obj2 = new Object();
        ReferenceQueue weakQueue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(obj2, weakQueue);
        obj2 = null;

        // 虚引用：在任何时候都可能被垃圾回收器回收
        Object obj3 = new Object();
        ReferenceQueue phantomQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(obj3, phantomQueue);
        obj3 = null;

        System.gc();

        System.out.println("softQueue.poll=" + softQueue.poll());
        System.out.println("softReference.get=" + softReference.get());

        System.out.println("weakQueue.poll=" + weakQueue.poll());
        System.out.println("weakReference.get=" + weakReference.get());

        System.out.println("phantomQueue.poll=" + phantomQueue.poll());
        System.out.println("phantomReference.get=" + phantomReference.get());
    }
}
