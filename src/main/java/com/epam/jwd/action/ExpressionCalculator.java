package com.epam.jwd.action;

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

    public int calculate(String expression) {
        ExpressionParser parser = ExpressionParser.reversePolishNotation();
        Expression parsedExpression = parser.parse(expression);
        return parsedExpression.result();
    }


}
