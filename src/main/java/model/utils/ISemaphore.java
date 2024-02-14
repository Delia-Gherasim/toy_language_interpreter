package model.utils;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISemaphore {
    void put(int key, Pair<Integer, List<Integer>> value) throws Exception;
    Pair<Integer, List<Integer>> get(int key) throws Exception;
    boolean containsKey(int key);
    int getFreeAddress();
    void setFreeAddress(int freeAddress);
    void update(int key, Pair<Integer, List<Integer>> value) throws Exception;
    HashMap<Integer, Pair<Integer, List<Integer>>> getSemaphoreTable();
    void setSemaphoreTable(HashMap<Integer, Pair<Integer, List<Integer>>> newSemaphoreTable);
    public Boolean isEmpty();
    public Set<Map.Entry<Integer,Pair<Integer, List<Integer>>>> entrySet();
}
