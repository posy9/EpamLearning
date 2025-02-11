package com.epam.jwd.repository;

import com.epam.jwd.exception.PlaneNotFoundException;
import com.epam.jwd.model.Plane;

import java.math.BigDecimal;
import java.util.List;

public interface PlaneRepository extends FigureRepository<Plane> {




    List<Plane> findPlanesByAngleWithCoordinatePlane(BigDecimal angle, Plane coordinatePlanes) throws PlaneNotFoundException;

    List<Plane> findOrthogonalPlanes() throws PlaneNotFoundException;

    List<Plane> sortPlanesById();

    List<Plane> sortPlanesByXCoordinateOfFirstDot();

    List<Plane> sortPlanesByYCoordinateOfFirstDot();

    List<Plane> sortPlanesByZCoordinateOfFirstDot();

}
