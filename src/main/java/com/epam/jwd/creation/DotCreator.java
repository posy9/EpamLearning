package com.epam.jwd.creation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.read.FileConverter;
import com.epam.jwd.validation.DotValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DotCreator {
    private static final Logger LOG = LogManager.getLogger(DotCreator.class);
    private static final String PATH_TO_FILE = "";

    private final DotValidator dotValidator = new DotValidator();


    public List<List<Dot>> createDots(FileConverter fileConverter)  {
        List<List<Dot>> dotsForPlane = new ArrayList<>();
        List<Dot> dots;
        List<List<Double>> expectedDotList = fileConverter.readFileAndParseToDouble(PATH_TO_FILE);
        for (List<Double> expectedDot : expectedDotList) {
            dots = new ArrayList<>();
            try{
                dotValidator.isValidDot(expectedDot);
                dots.add(new Dot(expectedDot.get(0), expectedDot.get(1), expectedDot.get(2)));
                dots.add(new Dot(expectedDot.get(3), expectedDot.get(4), expectedDot.get(5)));
                dots.add(new Dot(expectedDot.get(6), expectedDot.get(7), expectedDot.get(8)));
                dotsForPlane.add(dots);
                LOG.trace("Dots with coordinates x: {},y: {},z: {}, x: {},y: {},z: {}, x: {},y: {},z: {} are validated",
                        expectedDot.get(0), expectedDot.get(1), expectedDot.get(2), expectedDot.get(3), expectedDot.get(4),
                        expectedDot.get(5), expectedDot.get(6), expectedDot.get(7), expectedDot.get(8));

            }
            catch (IllegalDotCoordinatesException e) {
                LOG.error(e.getMessage());

            }

        }
        return dotsForPlane;
    }
}
