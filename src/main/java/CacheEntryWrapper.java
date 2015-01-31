import lombok.Getter;
import lombok.Setter;

/**
 * Created by shreyash on 27/1/15.
 */
@Getter
public class CacheEntryWrapper<K, V> {
    private final K key;
    private final V value;
    @Setter private double hValue;

    protected CacheEntryWrapper(K key, V value, double recomputationCost) {
        this.key = key;
        this.value = value;
        this.hValue = recomputationCost;
    }
}
