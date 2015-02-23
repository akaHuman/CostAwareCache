package com.cac.heap;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shreyash on 1/2/15.
 */
public class AugmentedMinHeapTest {

    @Test
    public void testInsertionDeletion() {
        AugmentedMinHeap<Integer> heap = new AugmentedMinHeap<>();

        for(int i = 10000; i > 0; i--) {
            heap.insert(i, i * 2);
        }

        for(int i = 0; i < 10000; i++) {
            Assert.assertEquals(heap.remove().getKey(), (Integer) (i + 1));
        }
    }

    @Test
    public void testIndex() {
        AugmentedMinHeap<Integer> heap = new AugmentedMinHeap<>();

        for(int i = 10000; i > 0; i--) {
            heap.insert(i, i * 2);
        }

        for(int i = 0; i < 10000; i++) {
            Assert.assertEquals(heap.getKeyIndex(heap.get(i).getKey()), i);
        }
    }
}
