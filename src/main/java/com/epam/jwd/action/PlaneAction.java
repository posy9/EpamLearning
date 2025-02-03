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

public class PlaneAction {

    private static final Logger LOG = LogManager.getLogger(PlaneAction.class);

    private Dot findVectorByCoordinates(Dot a, Dot b) {
        BigDecimal VectorX = a.getX().subtract(b.getX());
        BigDecimal VectorY = a.getY().subtract(b.getY());
        BigDecimal VectorZ = a.getZ().subtract(b.getZ());

        return new Dot(VectorX, VectorY, VectorZ);
    }

    private Dot findNormalVector(Plane plane) {

        Dot firstVector = findVectorByCoordinates(plane.getA(), plane.getB());
        Dot secondVector = findVectorByCoordinates(plane.getC(), plane.getB());

        BigDecimal normalVectorX = firstVector.getY().multiply(secondVector.getZ()).subtract(firstVector.getZ().multiply(secondVector.getY()));
        BigDecimal normalVectorY = firstVector.getZ().multiply(secondVector.getX()).subtract(firstVector.getX().multiply(secondVector.getZ()));
        BigDecimal normalVectorZ = firstVector.getX().multiply(secondVector.getY()).subtract(firstVector.getY().multiply(secondVector.getX()));

        return new Dot(normalVectorX, normalVectorY, normalVectorZ);
    }

    private BigDecimal findLengthOfVector(Dot vector) {
        BigDecimal xSquared = vector.getX().multiply(vector.getX());
        BigDecimal ySquared = vector.getY().multiply(vector.getY());
        BigDecimal zSquared = vector.getZ().multiply(vector.getZ());
        BigDecimal length = xSquared.add(ySquared).add(zSquared);
        return BigDecimal.valueOf(Math.sqrt(length.doubleValue()));
    }

    private BigDecimal scalarProduct(Dot v1, Dot v2) {
        BigDecimal xProduct = v1.getX().multiply(v2.getX());
        BigDecimal yProduct = v1.getY().multiply(v2.getY());
        BigDecimal zProduct = v1.getZ().multiply(v2.getZ());
        return xProduct.add(yProduct).add(zProduct);
    }

    public BigDecimal angleWithCoordinatePlane(Plane plane, Plane coordinatePlane) {
        PlaneValidator validator = new PlaneValidator();
        BigDecimal angleInDegrees;
        try {
            validator.isValidCoordinatePlane(coordinatePlane);

            Dot normalVector = findNormalVector(coordinatePlane);
            Dot normalVectorForPlane = findNormalVector(plane);
            BigDecimal normalVectorLength = findLengthOfVector(normalVectorForPlane);
            BigDecimal normalCoordinateVectorLength = findLengthOfVector(normalVector);
            BigDecimal scalarProduct = scalarProduct(normalVector, normalVectorForPlane);

            BigDecimal cosTheta = scalarProduct.divide(normalVectorLength.multiply(normalCoordinateVectorLength), MathContext.DECIMAL128);
            cosTheta = cosTheta.max(new BigDecimal("-1")).min(new BigDecimal("1")); // ограничение диапазона

            BigDecimal angleInRadians = BigDecimal.valueOf(Math.acos(cosTheta.doubleValue()));
            angleInDegrees = BigDecimal.valueOf(Math.toDegrees(angleInRadians.doubleValue())).setScale(2, RoundingMode.HALF_UP);

            if (angleInDegrees.compareTo(BigDecimal.valueOf(90)) > 0) {
                angleInDegrees = BigDecimal.valueOf(180).subtract(angleInDegrees);
            }


        } catch (IllegalCoordinatePlaneException e) {
            LOG.error(e.getMessage());
            return null;

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
