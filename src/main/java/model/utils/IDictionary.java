package model.utils;

import java.util.Map;
import java.util.Set;

public interface IDictionary<K, V> {
    boolean isDefined(K key);
    void put(K key, V value);
    V lookUp(K key);
    void update(K key, V value);
    boolean isEmpty();
    public Set<Map.Entry<K, V>> entrySet();
    Map<K, V> getContent();

}
