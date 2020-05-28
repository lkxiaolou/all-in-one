package org.newboo.guava;

import com.google.common.cache.CacheBuilder;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

public class Cache {

    public static void main(String[] args) {

        ConcurrentLinkedHashMap cache = new ConcurrentLinkedHashMap.Builder<String, Object>()
                .concurrencyLevel(4)
                .initialCapacity(5)
                .maximumWeightedCapacity(5)
                .build();
        for (int i = 0; i < 10; i++) {
            cache.put(i+"", i);
            cache.get(i+"");
        }

        com.google.common.cache.Cache cache1 = CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .initialCapacity(10)
                .maximumSize(10)
                .maximumWeight(10).build();

        cache1.put("1", "");
    }
}
