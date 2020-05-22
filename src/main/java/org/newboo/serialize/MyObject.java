package org.newboo.serialize;

public class MyObject {

    private MyObject() {
        if (myObject != null) {
            throw new RuntimeException("not allowed");
        }
    }

    private static MyObject myObject = new MyObject();

    public static MyObject getInstance() {
        return myObject;
    }
}
