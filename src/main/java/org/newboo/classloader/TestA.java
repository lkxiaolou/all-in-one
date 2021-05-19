package org.newboo.classloader;

public class TestA {
    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.hello();
    }

    public void hello() {
        System.out.println("TestA: " + this.getClass().getClassLoader());
        TestB testB = new TestB();
        testB.hello();
    }
}
