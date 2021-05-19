package org.newboo.classloader;

public class TestB {

    public void hello() {
        System.out.println("TestB: " + this.getClass().getClassLoader());
    }

}
