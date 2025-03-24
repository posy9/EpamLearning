package com.epam.jwd.calculator;

import com.epam.jwd.interpreter.Expression;
import com.epam.jwd.interpreter.ExpressionParser;

public class ExpressionCalculator {

    private static ExpressionCalculator instance;

    private ExpressionCalculator() {}

    public static ExpressionCalculator getInstance() {
        if (instance == null) {
            instance = new ExpressionCalculator();
        }
        return instance;
    }

    public String calculate(String expression) {
        ExpressionParser parser = ExpressionParser.reversePolishNotation();
        Expression parsedExpression = parser.parse(expression);
        return parsedExpression.result().toString();
    }
}
