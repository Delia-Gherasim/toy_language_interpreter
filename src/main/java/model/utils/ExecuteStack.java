package model.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ExecuteStack<T> implements IStack<T> {
    Stack<T> stack;
    public ExecuteStack() {
        stack = new Stack<T>();
    }
    @Override
    public void push(T elem) {
        stack.push(elem);
    }
    @Override
    public T pop() {
        return stack.pop();
    }
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    @Override
    public List<T> getReverse() {
        List<T> auxList = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(auxList);
        return auxList;
    }
    @Override
    public List<T> getContent() {
        return Arrays.asList((T[]) stack.toArray());
    }
    @Override
    public String toString() {
        return "stack: " + getReverse();
    }

}
