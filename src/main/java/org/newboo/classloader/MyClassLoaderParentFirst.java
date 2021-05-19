package org.newboo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoaderParentFirst extends ClassLoader {

    private final Map<String, String> classPathMap = new HashMap<>();

    private ClassLoader jdkClassLoader;

    public MyClassLoaderParentFirst(ClassLoader jdkClassLoader) {
        this.jdkClassLoader = jdkClassLoader;
        classPathMap.put("org.newboo.classloader.TestA", "/Users/didi/IdeaProjects/all-in-one/target/classes/org/newboo/classloader/TestA.class");
        classPathMap.put("org.newboo.classloader.TestB", "/Users/didi/IdeaProjects/all-in-one/target/classes/org/newboo/classloader/TestB.class");
    }

    // 重写了 findClass 方法
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }
        byte[] classBytes = getClassData(file);
        if (classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(null, classBytes, 0, classBytes.length);
    }

    private byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new
                ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = null;
        try {
            //这里要使用 JDK 的类加载器加载 java.lang 包里面的类
            result = jdkClassLoader.loadClass(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            return result;
        }
        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);
        if (classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
