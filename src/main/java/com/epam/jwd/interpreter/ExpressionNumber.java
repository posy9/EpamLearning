package com.epam.jwd.interpreter;

public class ExpressionNumber implements Expression {

    private final int value;

    public ExpressionNumber(int value) {
        this.value = value;
    }

    @Override
    public void interpret(ExpressionContext context) {
        context.push(value);
    }
}
