package org.newboo.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapDemo {


    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Person && ((Person) obj).name.equals(name);
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("aaa");
        Person p2 = new Person("aaa");

        Set<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);

        System.out.println("p1 " + p1);
        System.out.println("p2 " + p2);
        System.out.println(set.size());
    }

}
