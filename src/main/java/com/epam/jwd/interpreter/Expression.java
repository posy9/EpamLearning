package com.epam.jwd.interpreter;

public interface Expression {

    void interpret(ExpressionContext context);

    default Integer result() {
        ExpressionContext context = new ArrayDequeContext();
        this.interpret(context);
        return context.pop();
    }

}
