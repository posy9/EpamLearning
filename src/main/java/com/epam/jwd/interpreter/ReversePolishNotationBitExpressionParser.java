package com.epam.jwd.interpreter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotationBitExpressionParser implements ExpressionParser {

    private static ReversePolishNotationBitExpressionParser instance;

    private static final Pattern PATTERN_TO_FIND_ELEMENTS_OF_BIT_EXPRESSION = Pattern.compile("-?\\d+|[+\\-*/|&^()~]|<<|>>");

    private static final Expression SHIFT_LEFT_EXPRESSION = c -> {
        int secondOperand = c.pop();
        int firstOperand = c.pop();
        c.push(firstOperand << secondOperand);
    };

    private static final Expression SHIFT_RIGHT_EXPRESSION = c -> {
        int secondOperand = c.pop();
        int firstOperand = c.pop();
        c.push(firstOperand >> secondOperand);
    };

    private static final Expression BITWISE_AND_EXPRESSION = c -> c.push(c.pop() & c.pop());
    private static final Expression BITWISE_OR_EXPRESSION = c -> c.push(c.pop() | c.pop());
    private static final Expression BITWISE_NOT_EXPRESSION = c -> c.push(~c.pop());
    private static final Expression BITWISE_XOR_EXPRESSION = c -> c.push(c.pop() ^ c.pop());

    private static final int SHIFT_LEFT_PRIORITY = 4;
    private static final int SHIFT_RIGHT_PRIORITY = 4;
    private static final int BITWISE_AND_PRIORITY = 3;
    private static final int BITWISE_OR_PRIORITY = 1;
    private static final int BITWISE_NOT_PRIORITY = 5;
    private static final int BITWISE_XOR_PRIORITY = 2;

    private ReversePolishNotationBitExpressionParser() {}

    public static ReversePolishNotationBitExpressionParser getInstance() {
        if (instance == null) {
            instance = new ReversePolishNotationBitExpressionParser();
        }
        return instance;
    }

    @Override
    public Expression parse(String bitExpression) {
        List<String> elementsOfExpression = expressionToList(bitExpression);
        List<Expression> expressions = new ArrayList<>();
        Deque<String> operatorsStack = new ArrayDeque<>();

        for (String element : elementsOfExpression) {
            if (element.isEmpty()) {
                continue;
            }
            if (isNumber(element)) {
                final Scanner scan = new Scanner(element);
                expressions.add(new ExpressionNumber(scan.nextInt()));
            } else if (element.equals("(")) {
                operatorsStack.push(element);
            } else if (element.equals(")")) {
                while (!operatorsStack.isEmpty() && !operatorsStack.peek().equals("(")) {
                    expressions.add(getOperatorExpression(operatorsStack.pop()));
                }
                operatorsStack.pop();
            } else {
                while (!operatorsStack.isEmpty() && getOperatorPriority(element) <= getOperatorPriority(operatorsStack.peek())) {
                    expressions.add(getOperatorExpression(operatorsStack.pop()));
                }
                operatorsStack.push(element);
            }
        }
        while (!operatorsStack.isEmpty()) {
            expressions.add(getOperatorExpression(operatorsStack.pop()));
        }
        return c -> {
            for (Expression exp : expressions) {
                exp.interpret(c);
            }
        };
    }

    private boolean isNumber(String lexeme) {
        try {
            Integer.parseInt(lexeme);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int getOperatorPriority(String operator) {
        switch (operator) {
            case "<<" -> { return SHIFT_LEFT_PRIORITY; }
            case ">>" -> { return SHIFT_RIGHT_PRIORITY; }
            case "&" -> { return BITWISE_AND_PRIORITY; }
            case "|" -> { return BITWISE_OR_PRIORITY; }
            case "~" -> { return BITWISE_NOT_PRIORITY; }
            case "^" -> { return BITWISE_XOR_PRIORITY; }
            default -> { return -1; }
        }
    }

    private Expression getOperatorExpression(String operator) {
        switch (operator) {
            case "<<" -> { return SHIFT_LEFT_EXPRESSION; }
            case ">>" -> { return SHIFT_RIGHT_EXPRESSION; }
            case "&" -> { return BITWISE_AND_EXPRESSION; }
            case "|" -> { return BITWISE_OR_EXPRESSION; }
            case "~" -> { return BITWISE_NOT_EXPRESSION; }
            case "^" -> { return BITWISE_XOR_EXPRESSION; }
            default -> throw new IllegalArgumentException("Illegal operator : " + operator);
        }
    }

    private static List<String> expressionToList(String expression) {
        List<String> elements = new ArrayList<>();
        Matcher matcher = PATTERN_TO_FIND_ELEMENTS_OF_BIT_EXPRESSION.matcher(expression);
        while (matcher.find()) {
            elements.add(matcher.group());
        }
        return elements;
    }

}
