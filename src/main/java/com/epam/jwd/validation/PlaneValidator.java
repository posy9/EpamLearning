package com.epam.jwd.validation;

import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.exception.IllegalPlaneCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import com.epam.jwd.read.FileParser;

import java.math.BigDecimal;


public class PlaneValidator {

    private static PlaneValidator instance;

    private PlaneValidator() {

    }

    public static PlaneValidator getInstance() {
        if (instance == null) {
            instance = new PlaneValidator();
        }
        return instance;
    }

    public void isValidPlane(Dot a, Dot b, Dot c) throws IllegalPlaneCoordinatesException {

        BigDecimal v1x = b.getX().subtract(a.getX());
        BigDecimal v1y = b.getY().subtract(a.getY());
        BigDecimal v1z = b.getZ().subtract(a.getZ());

        BigDecimal v2x = c.getX().subtract(a.getX());
        BigDecimal v2y = c.getY().subtract(a.getY());
        BigDecimal v2z = c.getZ().subtract(a.getZ());

        BigDecimal i = v1y.multiply(v2z).subtract(v1z.multiply(v2y));
        BigDecimal j = v1z.multiply(v2x).subtract(v1x.multiply(v2z));
        BigDecimal k = v1x.multiply(v2y).subtract(v1y.multiply(v2x));


        if (i.compareTo(BigDecimal.ZERO) == 0 && j.compareTo(BigDecimal.ZERO) == 0 && k.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalPlaneCoordinatesException("Invalid plane with coordinates " + a + ", " + b + ", " + c + ".");
        }
    }

    public void isValidCoordinatePlane(Plane plane) throws IllegalCoordinatePlaneException {

        if (!((plane.getA().getX().compareTo(BigDecimal.ZERO) == 0
                && plane.getB().getX().compareTo(BigDecimal.ZERO) == 0
                && plane.getC().getX().compareTo(BigDecimal.ZERO) == 0) ||
                (plane.getA().getY().compareTo(BigDecimal.ZERO) == 0
                        && plane.getB().getY().compareTo(BigDecimal.ZERO) == 0
                        && plane.getC().getY().compareTo(BigDecimal.ZERO) == 0) ||
                (plane.getA().getZ().compareTo(BigDecimal.ZERO) == 0
                        && plane.getB().getZ().compareTo(BigDecimal.ZERO) == 0
                        && plane.getC().getZ().compareTo(BigDecimal.ZERO) == 0))) {
            throw new IllegalCoordinatePlaneException("Plane: " + plane + " is not a coordinate plane.");
        }
    }


}
