/*
import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

*/
/**
 * Created by shreyash on 27/1/15.
 *//*

public class CostAwareCache<K, V> {
    private final Map<K, CacheEntryWrapper<K, V>> cache;
    @Getter private final PriorityQueue<CacheEntryWrapper<K, V>> keyIndex;
    private final int DEFAULT_SIZE = 4;
    private final int MAX_SIZE = 1024;

    private final Comparator<CacheEntryWrapper<K, V>> comparator = new Comparator<CacheEntryWrapper<K, V>>() {
        @Override
        public int compare(CacheEntryWrapper<K, V> o1, CacheEntryWrapper<K, V> o2) {
            if (o1.getHValue() > o2.getHValue())
                return 1;
            else if (o1.getHValue() < o2.getHValue())
                return -1;
            return 0;
        }
    };

    @Getter private double lValue;
    @Getter private int cacheCapacity;

    public CostAwareCache() {
        this.cache = new HashMap<>(DEFAULT_SIZE);
        this.keyIndex = new PriorityQueue<>(DEFAULT_SIZE, comparator);
        this.cacheCapacity = DEFAULT_SIZE;
    }

    public CostAwareCache(int cacheSize) {
        if (cacheSize <= 0)
            cacheSize = DEFAULT_SIZE;
        else if (cacheSize > MAX_SIZE)
            cacheSize = MAX_SIZE;
        this.cache = new HashMap<>(cacheSize);
        this.keyIndex = new PriorityQueue<CacheEntryWrapper<K, V>>(cacheSize, comparator);
        this.cacheCapacity = cacheSize;
    }

    public void put(K key, V value, double cost) {
        if (key == null)
            throw new NullPointerException("Null keys not supported!");
        if (cost < 0)
            throw new IllegalArgumentException("Cost of an entry cannot be negative!");

        if (cache.containsKey(key)) {
            updateIndex(key, cost);
        } else {
            if (!cacheHasSpace()) {
                evict();
            }
            addToIndex(key, value, cost);
        }
    }

    private void updateIndex(K key, double cost) {
        CacheEntryWrapper<K, V> existingValue = cache.get(key);
        if (cost != existingValue.getHValue())
            existingValue.setHValue(existingValue.getHValue() + lValue);
        else // this case should not happen
            existingValue.setHValue(cost + lValue);
    }

    private void evict() {
        @SuppressWarnings("unchecked")
        CacheEntryWrapper<K, V> evictionCandidate = (CacheEntryWrapper<K, V>) keyIndex.peek();
        if (evictionCandidate != null) {
            keyIndex.remove();
            cache.remove(evictionCandidate.getKey());
            lValue = evictionCandidate.getHValue();
        }
    }

    private void addToIndex(K key, V value, double cost) {
        CacheEntryWrapper<K, V> newEntry = new CacheEntryWrapper<>(key, value, lValue + cost);
        keyIndex.add(newEntry);
        cache.put(key, newEntry);
    }

    private boolean cacheHasSpace() {
        return cache.size() < cacheCapacity;
    }

    public int size() {
        return cache.size();
    }

    public CacheEntryWrapper<K, V> get(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null!");

        CacheEntryWrapper<K, V> foundEntry = cache.get(key);
        if(foundEntry != null) {
            keyIndex.remove(foundEntry);
            foundEntry.setHValue(lValue + foundEntry.getHValue());
            keyIndex.add(foundEntry);
            return foundEntry;
        }
        return null;
    }
}
*/
