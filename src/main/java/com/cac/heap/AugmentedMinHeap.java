package com.cac.heap;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shreyash on 1/2/15.
 */
public class AugmentedMinHeap<K> {
    private List<HeapNode<K>> arrayList;
    private Map<K, Integer> arrayIndexMap;
    @Getter private int size;

    private final static int MIN_CAPACITY = 1 << 4;

    public AugmentedMinHeap() {
        this(MIN_CAPACITY);
    }

    public AugmentedMinHeap(int capacity) {
        this.arrayList = new ArrayList<>(capacity);
        this.arrayIndexMap = new HashMap<>(capacity);
        this.size = 0;
    }

    public void insert(K key, int cost) {
        HeapNode<K> newNode = new HeapNode<>(key, cost);
        arrayList.add(size, newNode);
        arrayIndexMap.put(key, size);
        size++;
        pushUp(size - 1);
    }

    public HeapNode<K> remove() {
        HeapNode<K> removedNode = arrayList.get(0);
        arrayIndexMap.remove(removedNode.getKey());
        size--;
        arrayList.set(0, arrayList.get(size));
        heapify(0);
        arrayList.set(size, null);
        return removedNode;
    }

    public void updateHValue(K key, int newHValue) {
        int keyIndex = arrayIndexMap.get(key);
        arrayList.get(keyIndex).setHValue(newHValue);
        heapify(keyIndex);
    }

    public HeapNode<K> peek() {
        return arrayList.get(0);
    }

    public HeapNode<K> get(int index) {
        if(index < size && index >= 0)
            return arrayList.get(index);
        throw new IndexOutOfBoundsException("Size of the heap is: " + size);
    }

    public int getKeyIndex(K key) {
        return arrayIndexMap.get(key);
    }

    private void pushUp(int nodeIndex) {
        int parentIndex;
        if(nodeIndex != 0) {
            parentIndex = (nodeIndex - 1)/2;
            HeapNode<K> currentNode = arrayList.get(nodeIndex);
            HeapNode<K> parentNode = arrayList.get(parentIndex);
            if(currentNode.compareTo(parentNode) < 0) {
                arrayList.set(nodeIndex, parentNode);
                arrayList.set(parentIndex, currentNode);
                arrayIndexMap.put(parentNode.getKey(), nodeIndex);
                arrayIndexMap.put(currentNode.getKey(), parentIndex);
                pushUp(parentIndex);
            }
        }
    }

    private void heapify(int rootIndex) {
        int smallest;
        int leftIndex = 2*rootIndex + 1;
        int rightIndex = 2*rootIndex + 2;

        HeapNode<K> currentNode = arrayList.get(rootIndex);

        if(leftIndex < size && currentNode.compareTo(arrayList.get(leftIndex)) > 0)
            smallest = leftIndex;
        else smallest = rootIndex;
        HeapNode<K> smallestNode = arrayList.get(smallest);

        if(rightIndex < size && smallestNode.compareTo(arrayList.get(rightIndex)) > 0)
            smallest = rightIndex;

        if(smallest != rootIndex) {
            arrayList.set(rootIndex, arrayList.get(smallest));
            arrayList.set(smallest, currentNode);
            arrayIndexMap.put(currentNode.getKey(), smallest);
            arrayIndexMap.put(smallestNode.getKey(), rootIndex);
            heapify(smallest);
        }
    }
}
