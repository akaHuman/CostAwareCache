package com.cac.heap;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by shreyash on 31/1/15.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"key"})
public class HeapNode<K> implements Comparable<HeapNode<K>> {
    private final K key;
    @Setter private int hValue;

    @Override
    public int compareTo(HeapNode<K> heapNode) {
        if(this.hValue > heapNode.getHValue()) return 1;
        else if(this.hValue < heapNode.getHValue()) return -1;
        return 0;
    }
}
