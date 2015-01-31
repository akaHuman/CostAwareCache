import lombok.AllArgsConstructor;
import lombok.ToString;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

/**
 * Created by shreyash on 30/1/15.
 */
public class TestHeap {

    @AllArgsConstructor
    @ToString
    class Point {
        private final int X;
    }

    @Test
    public void heapTest() {
        Heap<Point> heap = new Heap<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.X > o2.X) return 1;
                else if(o1.X < o2.X) return -1;
                return 0;
            }
        });

        for(int i = 1000; i > 0; i--) {
            heap.insert(new Point(i));
        }

        for(int i = 1; i <= 1000; i++) {
            Assert.assertEquals(heap.remove().X, i, "Assert failed at i = " + i);
        }
    }
}
