package com.epam.jwd.repository;

import com.epam.jwd.exception.PlaneCalculationResultNotFoundException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import com.epam.jwd.model.PlaneCalculationResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryPlaneCalculationResultsRepositoryTest {

    private static final int SOME_ID = 1;
    private final InMemoryPlaneCalculationResultsRepository repository = InMemoryPlaneCalculationResultsRepository.getInstance();
    private final InMemoryPlaneRepository planeRepository = InMemoryPlaneRepository.getInstance();

    @BeforeEach
    public void setUp() {
        planeRepository.clear();
    }

    @Test
    public void read_shouldThrowPlaneCalculationResultNotFoundException_whenIdDoesNotExist() {

        int inputId = 1;
        assertThrows(PlaneCalculationResultNotFoundException.class, () -> repository.read(inputId));

    }

    @Test
    public void read_shouldReturnPlaneCalculationResultForCorrectPlane_whenIdExists() {

        Plane inputPlane = new Plane(1, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));

        PlaneCalculationResults input = new PlaneCalculationResults(inputPlane);
        Plane savedPlane = planeRepository.create(inputPlane);
        PlaneCalculationResults savedPlaneCalculationResults = repository.read(savedPlane.getId());
        assertEquals(input, savedPlaneCalculationResults);

    }

    @Test
    public void planeToUpdate_shouldUpdatePlaneCalculationResultForCorrectPlane() {
        Plane inputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));

        Plane inputUpdatedPlane = new Plane(SOME_ID, new Dot(new BigDecimal("13.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("1.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("5.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));

        repository.planeToAdd(inputPlane);
        PlaneCalculationResults input = repository.read(inputPlane.getId());
        repository.planeToUpdate(inputUpdatedPlane);
        PlaneCalculationResults updatedInput = repository.read(inputUpdatedPlane.getId());
        assertEquals(input.getPlaneId(), updatedInput.getPlaneId());
    }

    @Test
    public void planeToUpdate_shouldUpdatePlaneCalculationResultWithCorrectInformation() {
        Plane inputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        repository.planeToAdd(inputPlane);
        Plane inputUpdatedPlane = new Plane(SOME_ID, new Dot(new BigDecimal("13.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("1.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("5.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        PlaneCalculationResults updatedInput = new PlaneCalculationResults(inputUpdatedPlane);

        repository.planeToUpdate(inputUpdatedPlane);
        PlaneCalculationResults updatedResult = repository.read(inputUpdatedPlane.getId());
        assertEquals(updatedInput, updatedResult);
    }

    @Test
    public void planeToAdd_shouldCreatePlaneCalculationResultForCorrectPlane() {
        Plane inputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        repository.planeToAdd(inputPlane);
        int resultId = repository.read(inputPlane.getId()).getPlaneId();
        assertEquals(inputPlane.getId(), resultId);
    }

    @Test
    public void planeToAdd_shouldCreateCorrectPlaneCalculationResult() {
        Plane inputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        PlaneCalculationResults inputPlaneCalculationResults = new PlaneCalculationResults(inputPlane);
        repository.planeToAdd(inputPlane);
        PlaneCalculationResults result = repository.read(inputPlane.getId());
        assertEquals(inputPlaneCalculationResults, result);
    }

    @Test
    public void planeToDelete_shouldDeletePlaneCalculationResultForCorrectPlane() {
        Plane inputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        repository.planeToAdd(inputPlane);
        repository.planeToDelete(inputPlane.getId());
        assertEquals(0, repository.size());

    }

    @Test
    public void callToClear_shouldClearPlaneCalculationResultsRepository() {
        repository.callToClear();
        assertEquals(0, planeRepository.size());
    }
}
