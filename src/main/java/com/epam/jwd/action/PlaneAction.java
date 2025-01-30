package com.epam.jwd.action;

import com.epam.jwd.exception.IllegalCoordinatePlaneException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlaneAction {

    private static final Logger LOG= LogManager.getLogger(PlaneAction.class);

    private static Dot findNormalVector(Plane plane) {
        double firstVectorX = plane.getA().getX() - plane.getB().getX();
        double firstVectorY = plane.getA().getY() - plane.getB().getY();
        double firstVectorZ = plane.getA().getZ() - plane.getB().getZ();
        double secondVectorX = plane.getC().getX() - plane.getB().getX();
        double secondVectorY = plane.getC().getY() - plane.getB().getY();
        double secondVectorZ = plane.getC().getZ() - plane.getB().getZ();
        Dot firstVector = new Dot(firstVectorX, firstVectorY, firstVectorZ);
        Dot secondVector = new Dot(secondVectorX, secondVectorY, secondVectorZ);

        double normalVectorX = firstVector.getY() * secondVector.getZ() - firstVector.getZ() * secondVector.getY();
        double normalVectorY = firstVector.getZ() * secondVector.getX() - firstVector.getX() * secondVector.getZ();
        double normalVectorZ = firstVector.getX() * secondVector.getY() - firstVector.getY() * secondVector.getX();

        return new Dot(normalVectorX, normalVectorY, normalVectorZ);
    }

    private static double findLengthOfVector(Dot vector) {
        return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
    }

    private static double scalarProduct(Dot v1, Dot v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    public  double angleWithCoordinatePlane(Plane plane, String coordinatePlane) throws IllegalCoordinatePlaneException {
        Dot normalVector = null;
        if (coordinatePlane.equalsIgnoreCase("xy")||coordinatePlane.equalsIgnoreCase("yx")) {

            normalVector=new Dot(0,0,1);
            LOG.trace("Normal vector for {} is founded",coordinatePlane);
        }
        else if (coordinatePlane.equalsIgnoreCase("xz")||coordinatePlane.equalsIgnoreCase("zx")) {
            normalVector=new Dot(0,1,0);
            LOG.trace("Normal vector for {} is founded",coordinatePlane);


        }
        else if (coordinatePlane.equalsIgnoreCase("yz")||coordinatePlane.equalsIgnoreCase("zy")) {
            normalVector=new Dot(1,0,0);
            LOG.trace("Normal vector for {} is founded",coordinatePlane);

        }
        else{
            throw new IllegalCoordinatePlaneException("Invalid coordinatePlane: "+coordinatePlane);
        }

        Dot normalVectorForPlane = findNormalVector(plane);
        double normalVectorLength = findLengthOfVector(normalVectorForPlane);
        double normalCoordinateVectorLength = findLengthOfVector(normalVector);
        double scalarProduct = scalarProduct(normalVector, normalVectorForPlane);
        double cosTheta = scalarProduct / (normalVectorLength * normalCoordinateVectorLength);
        cosTheta = Math.max(-1, Math.min(1, cosTheta));
        double angleInRadians = Math.acos(cosTheta);
        double angleInDegrees = Math.toDegrees(angleInRadians);
        if (angleInDegrees > 90) {
            angleInDegrees = 180-angleInDegrees;
        }
        return angleInDegrees;
    }

    public boolean isNormal(Plane plane) {
        Dot normalVector = findNormalVector(plane);
        return normalVector.getX() == 0 || normalVector.getY() == 0 || normalVector.getZ() == 0;
    }
}

