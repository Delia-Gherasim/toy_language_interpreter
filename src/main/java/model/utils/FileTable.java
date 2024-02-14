package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTable<K, V> implements IDictionary<K, V> {

    Map<K, V> fileT;

    public FileTable() {
        fileT = new HashMap<>();
    }

    @Override
    public boolean isDefined(K key) {
        return fileT.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        fileT.put(key, value);
    }

    @Override
    public V lookUp(K key) {
        return fileT.get(key);
    }

    @Override
    public void update(K key, V value) {
        fileT.put(key, value);
    }

    @Override
    public boolean isEmpty() {
        return fileT.isEmpty();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return fileT.entrySet();
    }

    @Override
    public Map<K, V> getContent() {
        return fileT;
    }

    @Override
    public String toString() {
        return "FileTable " + fileT;
    }

    public void delete(K key) {
        fileT.remove(key);
    }
}
