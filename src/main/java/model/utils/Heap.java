package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap<K,V> implements IHeap<K,V>{
    Map<K,V> heap;
    int last_address;
    public Heap() {
        heap = new HashMap<>();
        last_address = 0;
    }
    @Override
    public void updateLastAddress(int new_addr)
    {
        last_address = new_addr;
    }
    @Override
    public boolean isDefined(K key) {
        return heap.containsKey(key);
    }
    @Override
    public void put(K key, V value) {
        heap.put(key, value);
    }
    @Override
    public V lookUp(K key) { return heap.get(key); }
    @Override
    public void update(K key, V value) { heap.put(key, value); }
    @Override
    public Set<Map.Entry<K,V>> entrySet() { return heap.entrySet();}
    @Override
    public void remove(K key) { heap.remove(key); }
    @Override
    public String toString(){ return "heap= "+heap; }
    @Override
    public boolean isEmpty() { return heap.isEmpty(); }
    public void default_first_address() { last_address = 0; }
    public int getLastAddress() { return last_address; }
    public void setContent(Map<K, V> content) { heap = content; }
    public Map<K, V> getContent() { return heap; }

}