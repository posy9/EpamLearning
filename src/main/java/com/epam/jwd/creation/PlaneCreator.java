package com.epam.jwd.creation;

import com.epam.jwd.context.FigureContext;
import com.epam.jwd.exception.IllegalFigureNameException;
import com.epam.jwd.exception.IllegalPlaneCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.FigureFactory;
import com.epam.jwd.model.Plane;
import com.epam.jwd.validation.PlaneValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PlaneCreator {
    private static final Logger LOG = LogManager.getLogger(PlaneCreator.class);

    private final PlaneValidator planeValidator = PlaneValidator.getInstance();

    private static  PlaneCreator instance;

    private PlaneCreator() {

    }

    public static PlaneCreator getInstance() {
        if (instance == null) {
            instance = new PlaneCreator();
        }
        return instance;
    }

    public List<Plane> createPlanes(List<List<Dot>> dotsForPlane) {

        List<Plane> planes = new ArrayList<>();

        for (List<Dot> dots : dotsForPlane) {
            try {
                planeValidator.isValidPlane(dots.get(0), dots.get(1), dots.get(2));
                FigureContext planeContext = FigureContext.of("Plane", dots.get(0), dots.get(1), dots.get(2));
                Figure plane;
                plane = FigureFactory.newInstance().createFigure(planeContext);
                planes.add((Plane) plane);
                LOG.trace("Plane from dots {} was created", dots);
            } catch (IllegalFigureNameException | IllegalPlaneCoordinatesException e) {
                LOG.error(e.getMessage());
            }


        }
        return planes;
    }

}



