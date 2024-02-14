package model.utils;


import java.util.ArrayList;
import java.util.List;

public class Output<T> implements IList<T> {
    private final List<T> items;

    public Output() {
        items = new ArrayList<>();
    }

    @Override
    public void add(T itemToAdd) {
        items.add(itemToAdd);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "output: " + items;
    }
}
