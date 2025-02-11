package com.epam.jwd.model;

import com.epam.jwd.action.PlaneCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class PlaneCalculationResults {

    private static final Logger LOG = LogManager.getLogger(PlaneCalculationResults.class);

    private final PlaneCalculator planeCalculator =PlaneCalculator.getInstance();
    private Integer planeId;
    private BigDecimal angleWithXY;
    private BigDecimal angleWithXZ;
    private BigDecimal angleWithYZ;
    private boolean isNormal;


    public PlaneCalculationResults(Plane plane) {
        this.planeId = plane.getId();
        this.angleWithXY = planeCalculator.angleWithCoordinatePlane(plane, PlaneCalculator.XY_PLANE);
        this.angleWithXZ = planeCalculator.angleWithCoordinatePlane(plane, PlaneCalculator.XZ_PLANE);
        this.angleWithYZ = planeCalculator.angleWithCoordinatePlane(plane, PlaneCalculator.YZ_PLANE);
        this.isNormal = planeCalculator.isNormal(plane);
    }


    public BigDecimal getAngleWithXY() {
        return angleWithXY;
    }

    public Integer getPlaneId() {
        return planeId;
    }

    public BigDecimal getAngleWithXZ() {
        return angleWithXZ;
    }

    public BigDecimal getAngleWithYZ() {
        return angleWithYZ;
    }

    public boolean getIsNormal() {
        return isNormal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaneCalculationResults that = (PlaneCalculationResults) o;
        return isNormal == that.isNormal && Objects.equals(planeCalculator, that.planeCalculator) && Objects.equals(planeId, that.planeId) && Objects.equals(angleWithXY, that.angleWithXY) && Objects.equals(angleWithXZ, that.angleWithXZ) && Objects.equals(angleWithYZ, that.angleWithYZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planeCalculator, planeId, angleWithXY, angleWithXZ, angleWithYZ, isNormal);
    }

    @Override
    public String toString() {
        return "PlaneCalculationResults{" +
                "planeCalculator=" + planeCalculator +
                ", planeId=" + planeId +
                ", angleWithXY=" + angleWithXY +
                ", angleWithXZ=" + angleWithXZ +
                ", angleWithYZ=" + angleWithYZ +
                ", isNormal=" + isNormal +
                '}';
    }
}



