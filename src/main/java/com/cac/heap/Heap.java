package com.cac.heap;

import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shreyash on 30/1/15.
 */
public class Heap<K> {
    private HeapNode<K>[] array;
    private int capacity;
    private int size;
    private Comparator<HeapNode<K>> nodeComparator = new Comparator<HeapNode<K>>() {
        @Override
        public int compare(HeapNode<K> heapNode1, HeapNode<K> heapNode2) {
            if(heapNode1.getHValue() > heapNode2.getHValue()) return 1;
            else if(heapNode1.getHValue() < heapNode2.getHValue()) return -1;
            return 0;
        }
    };

    private static final int MIN_CAPACITY = 1 << 4;

    @Getter private Map<K, Integer> arrayIndexMap;

    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        this.capacity = capacity;
        this.array = (HeapNode<K>[]) new Object[this.capacity];
        this.arrayIndexMap = new HashMap<>(this.capacity);
        this.size = 0;
    }

    public Heap() {
        this(MIN_CAPACITY);
    }

    public void insert(HeapNode<K> node) {
        if(size == capacity)
            resize();
        array[size] = node;
        arrayIndexMap.put(node.getKey(), size);
        size++;
        pushUp(size - 1);
    }

    public HeapNode<K> remove() {
        HeapNode<K> valueRemoved = array[0];
        arrayIndexMap.remove(valueRemoved.getKey());
        array[0] = array[size - 1];
        size--;
        heapify(0);
        array[size] = null;
        return valueRemoved;
    }

    protected void updateHValue(K key, int newHValue) {
        Integer indexInHeap = arrayIndexMap.get(key);
        if(indexInHeap != null) {
            HeapNode<K> foundNode = array[indexInHeap];
            if(foundNode.getKey().equals(key)) {
                foundNode.setHValue(newHValue);
                heapify(indexInHeap);
            }
        } else throw new NullPointerException("No such key present!");
    }

    public int size() {
        return size;
    }

    public void dump() {
        for(int i = 0; i < size; i++)
            System.out.println(array[i]);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        HeapNode<K>[] tempArray = (HeapNode<K>[]) new Object[capacity * 2];
        System.arraycopy(array, 0, tempArray, 0, size);
        this.capacity = tempArray.length;
        this.array = tempArray;
    }

    private void pushUp(int nodeIndex) {
        int parentIndex;
        if(nodeIndex != 0) {
            parentIndex = (nodeIndex - 1)/2;
            if(nodeComparator.compare(array[nodeIndex], array[parentIndex]) < 0) {
                HeapNode<K> temp = array[nodeIndex];
                array[nodeIndex] = array[parentIndex];
                array[parentIndex] = temp;
                arrayIndexMap.put(array[parentIndex].getKey(), parentIndex);
                arrayIndexMap.put(array[nodeIndex].getKey(), nodeIndex);
                pushUp(parentIndex);
            }
        }
    }

    private void heapify(int rootIndex) {
        int smallest;
        int leftIndex = 2*rootIndex + 1;
        int rightIndex = 2*rootIndex + 2;

        if(leftIndex < size && nodeComparator.compare(array[rootIndex], array[leftIndex]) > 0)
            smallest = leftIndex;
        else smallest = rootIndex;
        if(rightIndex < size && nodeComparator.compare(array[smallest], array[rightIndex]) > 0)
            smallest = rightIndex;

        if(smallest != rootIndex) {
            HeapNode<K> temp = array[rootIndex];
            array[rootIndex] = array[smallest];
            array[smallest] = temp;
            arrayIndexMap.put(array[rootIndex].getKey(), rootIndex);
            arrayIndexMap.put(array[smallest].getKey(), smallest);
            heapify(smallest);
        }
    }

    public HeapNode<K>[] getArray() {
        return this.array;
    }
}
