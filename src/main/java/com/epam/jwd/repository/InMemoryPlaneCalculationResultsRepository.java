package com.epam.jwd.repository;

import com.epam.jwd.exception.PlaneCalculationResultNotFoundException;
import com.epam.jwd.exception.PlaneNotFoundException;
import com.epam.jwd.model.Plane;
import com.epam.jwd.model.PlaneCalculationResults;
import com.epam.jwd.observer.Subscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPlaneCalculationResultsRepository implements Subscriber {


    private static final String PLANE_CALCULATION_RESULT_NOT_FOUND_BY_ID_MSG = "Plane calculation result for plane with id %s not found";
    private static final Logger LOG = LogManager.getLogger(InMemoryPlaneCalculationResultsRepository.class);

    private final List<PlaneCalculationResults> planeCalculationResultsHolder;
    private static InMemoryPlaneCalculationResultsRepository instance;

    private InMemoryPlaneCalculationResultsRepository() {
        this.planeCalculationResultsHolder = new ArrayList<>();
    }


    public static InMemoryPlaneCalculationResultsRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryPlaneCalculationResultsRepository();
        }
        return instance;
    }

    public int size(){
        return planeCalculationResultsHolder.size();
    }

    public PlaneCalculationResults read(int id) {
        return findPlaneCalculationResultsById(id);
    }

    private void create(PlaneCalculationResults entity) {
        planeCalculationResultsHolder.add(entity);
    }


    private void update(PlaneCalculationResults entity) {
        final PlaneCalculationResults savedPlaneCalculationResults = read(entity.getPlaneId());
        final int planeCalculationResultsIndex = planeCalculationResultsHolder.indexOf(savedPlaneCalculationResults);
        planeCalculationResultsHolder.set(planeCalculationResultsIndex,entity);
    }


    private void delete(int id) {
        try {
            final PlaneCalculationResults planeCalculationResults = this.read(id);
            planeCalculationResultsHolder.remove(planeCalculationResults);
        } catch (PlaneCalculationResultNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    private void clear() {
        planeCalculationResultsHolder.clear();
    }


    private PlaneCalculationResults findPlaneCalculationResultsById(int id)  {
        for (PlaneCalculationResults planeCalculationResults : planeCalculationResultsHolder) {
            if (planeCalculationResults.getPlaneId().equals(id)) {
                return planeCalculationResults;
            }
        }
        throw new PlaneCalculationResultNotFoundException(String.format(PLANE_CALCULATION_RESULT_NOT_FOUND_BY_ID_MSG, id));
    }

    @Override
    public void planeToUpdate(Plane plane) {
        update(new PlaneCalculationResults(plane));
    }

    @Override
    public void planeToDelete(Integer id) {
        delete(id);
    }

    @Override
    public void planeToAdd(Plane plane) {
        create(new PlaneCalculationResults(plane));
    }

    @Override
    public void callToClear() {
        clear();
    }
}
