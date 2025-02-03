package com.epam.jwd.validation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class DotValidator {

    private static final int QUANTITY_OF_REQUIRED_PARAMETRES = 9;

    Logger LOG= LogManager.getLogger(DotValidator.class);

    public void isValidDot(List<BigDecimal> coordinates) throws IllegalDotCoordinatesException {
        if (coordinates.size()!=QUANTITY_OF_REQUIRED_PARAMETRES){
            throw new IllegalDotCoordinatesException("Invalid dot"+ coordinates);
        }
    }


}
