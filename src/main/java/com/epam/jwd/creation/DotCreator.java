package com.epam.jwd.creation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.read.FileParser;
import com.epam.jwd.validation.DotValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DotCreator {
    private static final Logger LOG = LogManager.getLogger(DotCreator.class);
    private static final String PATH_TO_FILE = "";
    private static  DotCreator instance;

    private DotCreator() {

    }

    public static DotCreator getInstance() {
        if (instance == null) {
            instance = new DotCreator();
        }
        return instance;
    }

    private final DotValidator dotValidator = DotValidator.getInstance();
    FileParser fileConverter = FileParser.getInstance();


    public List<List<Dot>> createDots(List<List<BigDecimal>> dotList)  {
        List<List<Dot>> dotsForPlane = new ArrayList<>();
        List<Dot> dots;

        for (List<BigDecimal> expectedDot : dotList) {
            dots = new ArrayList<>();
            try{
                dotValidator.isValidDot(expectedDot);
                for (int i = 0; i < expectedDot.size(); i += 3) {
                    dots.add(new Dot(expectedDot.get(i), expectedDot.get(i + 1), expectedDot.get(i + 2)));
                }
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
