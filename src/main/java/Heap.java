import java.util.Comparator;

/**
 * Created by shreyash on 30/1/15.
 */
public class Heap<T> {
    private T[] array;
    private int capacity;
    private int size;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
        this.comparator = comparator;
        this.size = 0;
    }

    public void insert(T value) {
        if(size == capacity)
            resize();
        array[size] = value;
        size++;
        pushUp(size - 1);
    }

    public T remove() {
        T valueRemoved = array[0];
        array[0] = array[size - 1];
        size--;
        heapify(0);
        array[size] = null;
        return valueRemoved;
    }

    protected void updateHValue(int keyIndex, int newHValue) {

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
        T[] tempArray = (T[]) new Object[capacity * 2];
        System.arraycopy(array, 0, tempArray, 0, size);
        this.capacity = tempArray.length;
        this.array = tempArray;
    }

    private void pushUp(int nodeIndex) {
        int parentIndex;
        if(nodeIndex != 0) {
            parentIndex = (nodeIndex - 1)/2;
            if(comparator.compare(array[nodeIndex], array[parentIndex]) < 0) {
                T temp = array[nodeIndex];
                array[nodeIndex] = array[parentIndex];
                array[parentIndex] = temp;
                pushUp(parentIndex);
            }
        }
    }

    private void heapify(int rootIndex) {
        int smallest;
        int leftIndex = 2*rootIndex + 1;
        int rightIndex = 2*rootIndex + 2;

        if(leftIndex < size && comparator.compare(array[rootIndex], array[leftIndex]) > 0)
            smallest = leftIndex;
        else smallest = rootIndex;
        if(rightIndex < size && comparator.compare(array[smallest], array[rightIndex]) > 0)
            smallest = rightIndex;

        if(smallest != rootIndex) {
            T temp = array[rootIndex];
            array[rootIndex] = array[smallest];
            array[smallest] = temp;
            heapify(smallest);
        }
    }
}
