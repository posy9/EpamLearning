package com.epam.jwd.validation;

import com.epam.jwd.exception.IllegalPlaneCoordinatesException;
import com.epam.jwd.model.Dot;


public class PlaneValidator {

    public void isValidPlane(Dot a, Dot b, Dot c) throws IllegalPlaneCoordinatesException {

        double v1x = b.getX() - a.getX();
        double v1y = b.getY() - a.getY();
        double v1z = b.getZ() - a.getZ();

        double v2x = c.getX() - a.getX();
        double v2y = c.getY() - a.getY();
        double v2z = c.getZ() - a.getZ();

        double i = v1y * v2z - v1z * v2y;
        double j = v1z * v2x - v1x * v2z;
        double k = v1x * v2y - v1y * v2x;


        if (i == 0 && j == 0 && k == 0) {
            throw new IllegalPlaneCoordinatesException("Invalid plane with coordinates " + a + ", " + b + ", " + c + ".");
        }
    }

}
