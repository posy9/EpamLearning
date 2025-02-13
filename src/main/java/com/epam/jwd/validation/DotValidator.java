package com.epam.jwd.validation;

import com.epam.jwd.creation.DotCreator;
import com.epam.jwd.exception.IllegalDotCoordinatesException;

import java.math.BigDecimal;
import java.util.List;

public class DotValidator {

    private static final int QUANTITY_OF_REQUIRED_PARAMETERS = 9;
    private static DotValidator instance;

    private DotValidator() {}

    public static DotValidator getInstance() {
        if (instance == null) {
            instance = new DotValidator();
        }
        return instance;
    }

    public void isValidDot(List<BigDecimal> coordinates) throws IllegalDotCoordinatesException {
        if (coordinates.size()!= QUANTITY_OF_REQUIRED_PARAMETERS){
            throw new IllegalDotCoordinatesException("Invalid dot"+ coordinates);
        }
    }
}
