package com.epam.jwd.model;

import com.epam.jwd.calculator.ExpressionCalculator;
import java.util.List;

public class BitExpressionComponent implements Component {

    private final String content;

    public BitExpressionComponent(String content) {
        this.content = content;
    }

    @Override
    public String getText() {
        return ExpressionCalculator.getInstance().calculate(content);
    }

    @Override
    public List<Component> getComponents() {
        throw new UnsupportedOperationException("Leaf component does not have components");
    }
}
