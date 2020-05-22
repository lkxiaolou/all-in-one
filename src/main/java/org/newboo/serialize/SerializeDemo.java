package org.newboo.serialize;

public class SerializeDemo {

    public static void main(String[] args) throws Exception {
        // 创建不了单例
        MyObject myObject = MyObject.class.newInstance();
        // 通过序列化&反序列化底层原理也是反射，代码略
    }
}
