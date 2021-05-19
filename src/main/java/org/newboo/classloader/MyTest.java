package org.newboo.classloader;

import java.lang.reflect.Method;

public class MyTest {
    public static void main(String[] args) throws Exception {
        MyClassLoaderParentFirst myClassLoaderParentFirst = new MyClassLoaderParentFirst(Thread.currentThread().getContextClassLoader().getParent());
        Class testAClass = myClassLoaderParentFirst.findClass("org.newboo.classloader.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, new Object[]{args});
    }
}
