package com.epam.jwd.action;

import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import com.epam.jwd.validation.PlaneValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Math.acos;

public class PlaneCalculator {

    public static final Plane XY_PLANE =new Plane(
            new Dot(new BigDecimal("2"), new BigDecimal("11.0"), new BigDecimal("0")),
            new Dot(new BigDecimal("6"), new BigDecimal("14.0"), new BigDecimal("0")),
            new Dot(new BigDecimal("7"), new BigDecimal("13.0"), new BigDecimal("0"))
    );
    public static final Plane XZ_PLANE = new Plane(
            new Dot(new BigDecimal("10.0"), new BigDecimal("0"), new BigDecimal("5")),
            new Dot(new BigDecimal("13.0"), new BigDecimal("0"), new BigDecimal("13")),
            new Dot(new BigDecimal("21.0"), new BigDecimal("0"), new BigDecimal("2"))
    );
    public static final Plane YZ_PLANE = new Plane(
            new Dot(new BigDecimal("3"), new BigDecimal("11.0"), new BigDecimal("0")),
            new Dot(new BigDecimal("5"), new BigDecimal("14.0"), new BigDecimal("0")),
            new Dot(new BigDecimal("4"), new BigDecimal("13.0"), new BigDecimal("0"))
    );

    private static PlaneCalculator instance;
    private static final Logger LOG = LogManager.getLogger(PlaneCalculator.class);

    private PlaneCalculator() {}

    public static PlaneCalculator getInstance() {
        if (instance == null) {
            instance = new PlaneCalculator();
        }
        return instance;
    }

    private Dot findVectorByCoordinates(Dot a, Dot b) {
        BigDecimal VectorXCoordinate = a.getX().subtract(b.getX());
        BigDecimal VectorYCoordinate = a.getY().subtract(b.getY());
        BigDecimal VectorZCoordinate = a.getZ().subtract(b.getZ());

        return new Dot(VectorXCoordinate, VectorYCoordinate, VectorZCoordinate);
    }

    private Dot findNormalVector(Plane plane) {
        Dot firstVectorCoordinates = findVectorByCoordinates(plane.getA(), plane.getB());
        Dot secondVector = findVectorByCoordinates(plane.getC(), plane.getB());

        BigDecimal normalVectorXCoordinate = firstVectorCoordinates.getY().multiply(secondVector.getZ()).subtract(firstVectorCoordinates.getZ().multiply(secondVector.getY()));
        BigDecimal normalVectorYCoordinate = firstVectorCoordinates.getZ().multiply(secondVector.getX()).subtract(firstVectorCoordinates.getX().multiply(secondVector.getZ()));
        BigDecimal normalVectorZCoordinate = firstVectorCoordinates.getX().multiply(secondVector.getY()).subtract(firstVectorCoordinates.getY().multiply(secondVector.getX()));

        return new Dot(normalVectorXCoordinate, normalVectorYCoordinate, normalVectorZCoordinate);
    }

    private BigDecimal findLengthOfVector(Dot vector) {
        BigDecimal xSquared = vector.getX().multiply(vector.getX());
        BigDecimal ySquared = vector.getY().multiply(vector.getY());
        BigDecimal zSquared = vector.getZ().multiply(vector.getZ());
        BigDecimal length = xSquared.add(ySquared).add(zSquared);
        return length.sqrt(MathContext.DECIMAL128);
    }

    private BigDecimal scalarProduct(Dot v1, Dot v2) {
        BigDecimal xProduct = v1.getX().multiply(v2.getX());
        BigDecimal yProduct = v1.getY().multiply(v2.getY());
        BigDecimal zProduct = v1.getZ().multiply(v2.getZ());
        return xProduct.add(yProduct).add(zProduct);
    }

    public BigDecimal angleWithCoordinatePlane(Plane plane, Plane coordinatePlane) {
        PlaneValidator validator = PlaneValidator.getInstance();
        BigDecimal angleInDegrees;
        BigDecimal angleInRadians;
        try {
            validator.isValidCoordinatePlane(coordinatePlane);

            Dot normalVector = findNormalVector(coordinatePlane);
            Dot normalVectorForPlane = findNormalVector(plane);

            BigDecimal normalVectorLength = findLengthOfVector(normalVectorForPlane);
            BigDecimal normalCoordinateVectorLength = findLengthOfVector(normalVector);
            BigDecimal scalarProduct = scalarProduct(normalVector, normalVectorForPlane);

            BigDecimal cosTheta = scalarProduct.divide(normalVectorLength.multiply(normalCoordinateVectorLength), MathContext.DECIMAL128);

            cosTheta = cosTheta.max(new BigDecimal("-1")).min(new BigDecimal("1"));

            angleInRadians = BigDecimal.valueOf(acos(cosTheta.doubleValue()));
            angleInDegrees = BigDecimal.valueOf(Math.toDegrees(angleInRadians.doubleValue())).setScale(2, RoundingMode.HALF_UP);

            if (angleInDegrees.compareTo(BigDecimal.valueOf(90)) > 0) {
                angleInDegrees = BigDecimal.valueOf(180).subtract(angleInDegrees);
            }
        } catch (IllegalCoordinatePlaneException e) {
            LOG.error(e.getMessage());
            throw new IllegalCoordinatePlaneException(e.getMessage());
        }
        return angleInDegrees;
    }

    public boolean isNormal(Plane plane) {
        Dot normalVector = findNormalVector(plane);
        return normalVector.getX().compareTo(BigDecimal.ZERO) == 0
                || normalVector.getY().compareTo(BigDecimal.ZERO) == 0
                || normalVector.getZ().compareTo(BigDecimal.ZERO) == 0;
    }
}



