package model.utils;

import java.util.Map;
import java.util.Set;

public interface IHeap<K,V> {
    public boolean isEmpty();
    public void default_first_address();
    public int getLastAddress();
    public void updateLastAddress(int new_addr);
    boolean isDefined(K key);
    void put(K key, V value);
    V lookUp(K key);
    void update(K key, V value);
    void remove(K key);
    public void setContent(Map<K, V> content);
    public Map<K, V> getContent();
    public Set<Map.Entry<K,V>> entrySet();
}