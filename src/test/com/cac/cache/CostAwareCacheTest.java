package com.cac.cache;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shreyash on 1/2/15.
 */
public class CostAwareCacheTest {

    @Test
    public void testPutAndGet() {
        CostAwareCache<Integer, Integer> cache = new CostAwareCache<>(16);

        for(int i = 16; i > 0; i--) {
            cache.put(i, i, i * 2);
        }

        for(int i = 1; i <= 16; i++)
            Assert.assertEquals(cache.get(i), (Integer) i);
    }

    @Test
    public void testEviction() {
        CostAwareCache<Integer, Integer> cache = new CostAwareCache<>(16);

        for(int i = 16; i > 0; i--) {
            cache.put(i, i, i * 2);
        }

        cache.put(17, 17, 34);
        Assert.assertEquals(cache.get(1), null);
        Assert.assertEquals(cache.get(17), (Integer) 17);
    }

    @Test
    public void testEvictionWhenKeysHaveSameCost() {
        CostAwareCache<Integer, Integer> cache = new CostAwareCache<>(3);

        for(int i = 3; i > 0; i--) {
            cache.put(i, i, 100);
        }

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.put(4, 4, 100);
        Assert.assertEquals(cache.get(3), null);
        Assert.assertEquals(cache.get(4), (Integer)4);
        Assert.assertEquals(cache.get(2), (Integer)2);
        Assert.assertEquals(cache.get(1), (Integer)1);
    }
}
