import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shreyash on 30/1/15.
 */
@AllArgsConstructor
@Getter
class HeapNode<K> {
    private int hValue;
    private final K key;
}

@AllArgsConstructor
@Getter
class ValueWrapper<V> {
    private final V value;
    private final int heapIndex;
}

public class CostAwareCache<K, V> {
    private int lValue = 0;
    private final Map<K, ValueWrapper<V>> cache;
    private final Heap<HeapNode<K>> keyIndex;
    private int capacity;

    private final int MIN_CAPACITY = 1 << 4;
    private final int MAX_CAPACITY = 1 << 30;

    public CostAwareCache(int initCapacity) {
        if(initCapacity < MIN_CAPACITY )
            this.capacity = MIN_CAPACITY;
        else if(initCapacity > MAX_CAPACITY)
            this.capacity = MAX_CAPACITY;
        else this.capacity = initCapacity;

        this.cache = new HashMap<>(capacity);
        this.keyIndex = new Heap<>(capacity, new Comparator<HeapNode<K>>() {
            @Override
            public int compare(HeapNode<K> heapNode1, HeapNode<K> heapNode2) {
                if(heapNode1.getHValue() > heapNode2.getHValue()) return 1;
                else if(heapNode1.getHValue() < heapNode2.getHValue()) return -1;
                return 0;
            }
        });
    }

    public CostAwareCache() {
        this.capacity = MIN_CAPACITY;
        this.keyIndex = new Heap<>(capacity, new Comparator<HeapNode<K>>() {
            @Override
            public int compare(HeapNode<K> heapNode1, HeapNode<K> heapNode2) {
                if(heapNode1.getHValue() > heapNode2.getHValue()) return 1;
                else if(heapNode1.getHValue() < heapNode2.getHValue()) return -1;
                return 0;
            }
        });
        this.cache = new HashMap<>(capacity);
    }

    public void insert(K key, V value, int cost) {
        if(cache.containsKey(key)) {
            keyIndex.updateHValue(cache.get(key).getHeapIndex(), lValue + cost);
        } else {
            if(cache.size() == capacity) {
                HeapNode<K> minHNode = keyIndex.remove();
                lValue = minHNode.getHValue();
                cache.put(key, new ValueWrapper<V>());

            }
        }

        if(keyIndex.size() < capacity) {

            cache.put(key, value);
            keyIndex.insert(new HeapNode<K>());
        }
    }
}
