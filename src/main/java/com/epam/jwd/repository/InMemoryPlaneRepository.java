package com.epam.jwd.repository;

import com.epam.jwd.action.PlaneCalculator;
import com.epam.jwd.exception.PlaneNotFoundException;
import com.epam.jwd.model.Plane;
import com.epam.jwd.observer.Publisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InMemoryPlaneRepository implements PlaneRepository, Publisher {

    private static final Logger LOG = LogManager.getLogger(InMemoryPlaneRepository.class);
    private static final String PLANE_NOT_FOUND_BY_ID_MSG = "Plane with id %s not found";

    private final ArrayList<Plane> planeHolder;
    private int maxId;
    private final InMemoryPlaneCalculationResultsRepository planeCalculationResultsHolder =
            InMemoryPlaneCalculationResultsRepository.getInstance();

    private static InMemoryPlaneRepository instance;

    private InMemoryPlaneRepository() {
        this.planeHolder = new ArrayList<>();
    }


    public static InMemoryPlaneRepository getInstance() {
        if (instance == null) {
                    instance = new InMemoryPlaneRepository();
        }
        return instance;
    }

    @Override
    public Plane create(Plane plane) {
        maxId++;
        final Plane planeWithId = plane.withId(maxId);
        planeHolder.add(planeWithId);
        notifyAboutNewPlane(planeWithId);

        return planeWithId;
    }

    @Override
    public Plane read(int id) throws PlaneNotFoundException {
        return findPlaneById(id);
    }

    @Override
    public Plane update(Plane plane) throws PlaneNotFoundException {
        final Plane savedPlane = read(plane.getId());
        final int planeIndex = planeHolder.indexOf(savedPlane);
        planeHolder.set(planeIndex,plane);
        notifyAboutPlaneUpdate(plane);
        return plane;
    }

    @Override
    public void delete(int id) {
            final Plane plane = read(id);
            notifyAboutPlaneDelete(id);
            planeHolder.remove(plane);
    }

    public void clear(){
        planeHolder.clear();
        maxId = 0;
        notifyAboutClear();
    }

    public int size(){
        return planeHolder.size();
    }


    @Override
    public List<Plane> findPlanesByAngleWithCoordinatePlane(BigDecimal angle, Plane coordinatePlane) throws PlaneNotFoundException {
        List<Plane> result = new ArrayList<>();
        for (Plane plane : planeHolder) {
            if(FindAngleToCheck(plane,coordinatePlane).equals(angle)) {
                result.add(plane);
            }
        }
        if (result.isEmpty()){
            throw new PlaneNotFoundException("No planes that match the given request");
        }
        else {
            return result;
        }
    }

    @Override
    public List<Plane> findOrthogonalPlanes() throws PlaneNotFoundException {
        List<Plane> result = new ArrayList<>();
        for (Plane plane : planeHolder) {
            if(planeCalculationResultsHolder.read(plane.getId()).getIsNormal()){
                result.add(plane);
            }
        }
        if (result.isEmpty()){
            throw new PlaneNotFoundException("No planes that are orthogonal to coordinate planes");
        }
        else {
            return result;
        }
    }

    @Override
    public List<Plane> sortPlanesById() {
        planeHolder.sort(Comparator.comparing(Plane::getId));
        return planeHolder;
    }

    @Override
    public List<Plane> sortPlanesByXCoordinateOfFirstDot() {
        planeHolder.sort(Comparator.comparing(plane -> plane.getA().getX()));
        return planeHolder;
    }

    @Override
    public List<Plane> sortPlanesByYCoordinateOfFirstDot() {
        planeHolder.sort(Comparator.comparing(plane -> plane.getA().getY()));
        return planeHolder;
    }

    @Override
    public List<Plane> sortPlanesByZCoordinateOfFirstDot() {
        planeHolder.sort(Comparator.comparing(plane -> plane.getA().getZ()));
        return planeHolder;
    }


    @Override
    public void notifyAboutPlaneDelete(Integer planeId) {
        planeCalculationResultsHolder.planeToDelete(planeId);
    }

    @Override
    public void notifyAboutNewPlane(Plane plane) {
        planeCalculationResultsHolder.planeToAdd(plane);
    }

    @Override
    public void notifyAboutPlaneUpdate(Plane plane) {
        planeCalculationResultsHolder.planeToUpdate(plane);
    }

    @Override
    public void notifyAboutClear() {
        planeCalculationResultsHolder.callToClear();
    }

    private BigDecimal FindAngleToCheck(Plane plane, Plane coordinatePlane) throws PlaneNotFoundException {
        if (coordinatePlane == PlaneCalculator.YZ_PLANE) {
            return planeCalculationResultsHolder.read(plane.getId()).getAngleWithYZ();
        }else if (coordinatePlane == PlaneCalculator.XZ_PLANE) {
            return planeCalculationResultsHolder.read(plane.getId()).getAngleWithXZ();
        }
        else if (coordinatePlane == PlaneCalculator.XY_PLANE){
            return planeCalculationResultsHolder.read(plane.getId()).getAngleWithXY();
        }
        else {
            throw new PlaneNotFoundException("This plane is not coordinate");
        }
    }

    private Plane findPlaneById(int id) throws PlaneNotFoundException {
        for (Plane plane : planeHolder) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        throw new PlaneNotFoundException(String.format(PLANE_NOT_FOUND_BY_ID_MSG, id));
    }


}
