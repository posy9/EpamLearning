package com.epam.jwd.interpreter;

public interface ExpressionContext {

    int pop();

    void push(int value);

}