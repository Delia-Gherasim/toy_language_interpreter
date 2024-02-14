package model.utils;

import javafx.util.Pair;
import model.exceptions.ExceptionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SemaphoreTable implements ISemaphore{
    private HashMap<Integer, Pair<Integer, List<Integer>>> semaphoreTable;
    private int freeLocation = 0;

    public SemaphoreTable() {
        this.semaphoreTable = new HashMap<>();
    }

    @Override
    public void put(int key, Pair<Integer, List<Integer>> value) throws Exception {
        synchronized (this) {
            if (!semaphoreTable.containsKey(key)) {
                semaphoreTable.put(key, value);
            } else {
                throw new ExceptionUtils("Semaphore table already contains the key "+ key);
            }
        }
    }

    @Override
    public Pair<Integer, List<Integer>> get(int key) throws Exception {
        synchronized (this) {
            if (semaphoreTable.containsKey(key)) {
                return semaphoreTable.get(key);
            } else {
                throw new ExceptionUtils("Semaphore table doesn't contain the key "+ key);
            }
        }
    }

    @Override
    public boolean containsKey(int key) {
        synchronized (this) {
            return semaphoreTable.containsKey(key);
        }
    }

    @Override
    public int getFreeAddress() {
        synchronized (this) {
            freeLocation++;
            return freeLocation;
        }
    }

    @Override
    public void setFreeAddress(int freeAddress) {
        synchronized (this) {
            this.freeLocation = freeAddress;
        }
    }

    @Override
    public void update(int key, Pair<Integer, List<Integer>> value) throws Exception {
        synchronized (this) {
            if (semaphoreTable.containsKey(key)) {
                semaphoreTable.replace(key, value);
            } else {
                throw new ExceptionUtils("Semaphore table doesn't contain key "+ key);
            }
        }
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getSemaphoreTable() {
        synchronized (this) {
            return semaphoreTable;
        }
    }

    @Override
    public void setSemaphoreTable(HashMap<Integer, Pair<Integer, List<Integer>>> newSemaphoreTable) {
        synchronized (this) {
            this.semaphoreTable = newSemaphoreTable;
        }
    }

    @Override
    public Boolean isEmpty() {
        return semaphoreTable.isEmpty();
    }

    @Override
    public Set<Map.Entry<Integer, Pair<Integer, List<Integer>>>> entrySet() {
        return semaphoreTable.entrySet();
    }

    @Override
    public String toString() {
        return semaphoreTable.toString();
    }
}