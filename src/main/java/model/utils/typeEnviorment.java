package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class typeEnviorment<K, V> implements IDictionary<K,V>{
    Map<K, V> dictionary;
    public typeEnviorment() {
        dictionary = new HashMap<>();
    }
    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }
    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }
    @Override
    public V lookUp(K key) {
        return dictionary.get(key);
    }
    @Override
    public void update(K key, V value) {
        dictionary.put(key, value);
    }
    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dictionary.entrySet();
    }
    @Override
    public java.lang.String toString() {
        return "dictionary= " + dictionary;
    }
    public Map<K,V> getContent(){
        return dictionary;
    }


}
