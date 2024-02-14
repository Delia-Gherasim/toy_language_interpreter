package model.utils;

import model.statements.IStatement;

import java.util.List;

public interface IStack<T> {
    void push(T elem);

    T pop();

    boolean isEmpty();

    List<T> getReverse();

    List<T> getContent();
}
