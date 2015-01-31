import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shreyash on 28/1/15.
 */
@Slf4j
public class TestCache {

    @Test
    public void test() {
        CostAwareCache<Integer, Integer> cache = new CostAwareCache<>();
        Assert.assertEquals(cache.getCacheCapacity(), 4);

        cache.put(1, 3, 4);
        Assert.assertEquals(cache.getLValue(), 0D);
        Assert.assertEquals(cache.get(1).getHValue(), 4D);

        cache.put(2, 3, 4.3);
        Assert.assertEquals(cache.getLValue(), 0D);
        Assert.assertEquals(cache.get(2).getHValue(), 4.3);

        cache.put(3, 3, 2.7);
        Assert.assertEquals(cache.getLValue(), 0D);
        Assert.assertEquals(cache.get(3).getHValue(), 2.7);

        cache.put(4, 3, 1.4);
        Assert.assertEquals(cache.getLValue(), 0D);
        Assert.assertEquals(cache.get(4).getHValue(), 1.4);

        CacheEntryWrapper<Integer, Integer> result = cache.get(2);
        Assert.assertEquals(result.getValue(), 3.1);
        Assert.assertEquals(result.getHValue(), 4.3);
        Assert.assertEquals(cache.getLValue(), 0D);

        cache.put(5, 3, 3.1);
        Assert.assertEquals(cache.getLValue(), 1.4);
        Assert.assertEquals(cache.get(5).getHValue(), 5.9);
        Assert.assertEquals(cache.get(4), null);

        result = cache.get(2);
        Assert.assertEquals(result.getHValue(), 4.3 + 1.4);
        Assert.assertEquals(result.getValue(), 3.1);
        Assert.assertEquals(cache.getLValue(), 1.4);


    }
}
