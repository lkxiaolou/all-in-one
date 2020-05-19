package org.newboo.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadContext {

    private static final ThreadLocal<ThreadContext> LOCAL = new ThreadLocal<ThreadContext>() {
        @Override
        protected ThreadContext initialValue() {
            return new ThreadContext();
        }
    };

    private static final InheritableThreadLocal<ThreadContext> INHERITABLE_LOCAL = new InheritableThreadLocal<ThreadContext>() {
        @Override
        protected ThreadContext initialValue() {
            return new ThreadContext();
        }
    };

    public static ThreadLocal<ThreadContext> getLocal() {
        return LOCAL;
    }

    public static InheritableThreadLocal<ThreadContext> getInheritableLocal() {
        return INHERITABLE_LOCAL;
    }

    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ThreadContext{" +
                "map=" + map +
                '}';
    }
}
