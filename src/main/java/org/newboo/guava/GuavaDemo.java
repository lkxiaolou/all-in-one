package org.newboo.guava;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class GuavaDemo {

    private static class MyClass{
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static void main(String[] args) {
        MyClass my = null;
        try {
            // 抛出空指针异常
            Optional.of(my).get().getCount();
        } catch (NullPointerException e) {

        }

        // null -> false
        System.out.println(Optional.fromNullable(my).isPresent());
        // not null -> true
        System.out.println(Optional.fromNullable(new MyClass()).isPresent());

        // 字符串处理
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull("lk"));

    }

}
