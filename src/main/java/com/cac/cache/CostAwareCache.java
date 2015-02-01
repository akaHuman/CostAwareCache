package com.cac.cache;

import com.cac.heap.AugmentedMinHeap;
import com.cac.heap.HeapNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shreyash on 30/1/15.
 */
public class CostAwareCache<K, V> {
    private int lValue = 0;
    private final Map<K, V> cache;
    private final AugmentedMinHeap<K> keyIndex;
    private int capacity;

    private static final int MIN_CAPACITY = 1 << 1;
    private static final int MAX_CAPACITY = 1 << 30;

    public CostAwareCache(int initCapacity) {
        if(initCapacity < MIN_CAPACITY )
            this.capacity = MIN_CAPACITY;
        else if(initCapacity > MAX_CAPACITY)
            this.capacity = MAX_CAPACITY;
        else this.capacity = initCapacity;

        this.cache = new HashMap<>(capacity);
        this.keyIndex = new AugmentedMinHeap<>(capacity);
    }

    public CostAwareCache() {
        this(MIN_CAPACITY);
    }

    public void put(K key, V value, int cost) {
        if(cache.containsKey(key)) {
            keyIndex.updateHValue(key, lValue + cost);
        } else {
            if (cache.size() == capacity) {
                HeapNode<K> minHNode = keyIndex.remove();
                cache.remove(minHNode.getKey());
                lValue = minHNode.getHValue();
            }
            cache.put(key, value);
            keyIndex.insert(key, lValue + cost);
        }
    }

    public V get(K key) {
        V value = null;
        if(key != null && cache.containsKey(key)) {
            value = cache.get(key);
            int currentHValue = keyIndex.get(keyIndex.getKeyIndex(key)).getHValue();
            keyIndex.updateHValue(key, lValue + currentHValue);
        }
        return value;
    }
}
