package com.epam.jwd.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeContext implements ExpressionContext {

    private final Deque<Integer> deque = new ArrayDeque<>();

    @Override
    public int pop() {
        return deque.pop();
    }

    @Override
    public void push(int value) {
        deque.push(value);
    }
}