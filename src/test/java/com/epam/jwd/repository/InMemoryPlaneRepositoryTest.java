package com.epam.jwd.repository;

import com.epam.jwd.action.PlaneCalculator;
import com.epam.jwd.exception.PlaneNotFoundException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryPlaneRepositoryTest {

    private final InMemoryPlaneRepository planeRepository = InMemoryPlaneRepository.getInstance();
    private static final int SOME_ID = 1;
    private static final BigDecimal TEST_ANGLE = new BigDecimal("36.09");

    @BeforeEach
    public void setUp() {
        planeRepository.clear();
    }

    @Test
    public void create_shouldCreatePlaneWithFirstId_whenRepositoryIsEmpty() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        Plane resultPlane = planeRepository.create(inputPlane);
        assertEquals(1, resultPlane.getId());
    }

    @Test
    public void create_shouldCreatePlaneWithNextId_whenRepositoryHasOtherPlanes() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        Plane newInputPlane = new Plane(new Dot(new BigDecimal("15.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        newInputPlane = planeRepository.create(newInputPlane);
        assertEquals(4, newInputPlane.getId());
    }

    @Test
    public void create_shouldCreatePlaneWithCorrectInformation() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        Plane result = planeRepository.create(inputPlane);
        assertEquals(inputPlane, result);
    }

    @Test
    public void read_shouldReturnPlaneWithCorrectInformation_whenIdExists() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        inputPlane = planeRepository.create(inputPlane);
        Plane result = planeRepository.read(inputPlane.getId());
        assertEquals(inputPlane, result);
    }

    @Test
    public void read_shouldReturnPlaneWithCorrectId_whenIdExists() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        inputPlane = planeRepository.create(inputPlane);
        Plane result = planeRepository.read(inputPlane.getId());
        assertEquals(inputPlane.getId(), result.getId());
    }

    @Test
    public void read_shouldThrowException_whenIdDoesNotExist() {
        assertThrows(PlaneNotFoundException.class, () -> planeRepository.read(SOME_ID));
    }

    @Test
    public void update_shouldUpdatePlaneWithCorrectInformation_whenIdExists() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        Plane secondInputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));

        planeRepository.create(firstInputPlane);
        Plane result = planeRepository.update(secondInputPlane);
        assertEquals(secondInputPlane, result);
    }

    @Test
    public void update_shouldUpdatePlaneWithCorrectId_whenIdExists() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        Plane secondInputPlane = new Plane(SOME_ID, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        firstInputPlane = planeRepository.create(firstInputPlane);
        Plane result = planeRepository.update(secondInputPlane);
        assertEquals(firstInputPlane.getId(), result.getId());
    }

    @Test
    public void update_shouldThrowException_whenIdDoesNotExist() {
        Plane inputPlane = new Plane(1, new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        assertThrows(PlaneNotFoundException.class, () -> planeRepository.update(inputPlane));
    }

    @Test
    public void delete_shouldThrowException_whenIdDoesNotExist() {
        assertThrows(PlaneNotFoundException.class, () -> planeRepository.delete(SOME_ID));
    }

    @Test
    public void delete_shouldDeletePlaneWithCorrectId_whenIdExists() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        inputPlane = planeRepository.create(inputPlane);
        planeRepository.delete(inputPlane.getId());
        assertEquals(0, planeRepository.size());
    }

    @Test
    public void clear_shouldClearPlaneRepository() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        planeRepository.clear();
        assertEquals(0, planeRepository.size());
    }

    @Test
    public void size_shouldReturnSizeOfPlaneRepository() {
        Plane inputPlane = new Plane(new Dot(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        planeRepository.create(inputPlane);
        assertEquals(3, planeRepository.size());
    }

    @Test
    public void findPlanesByAngleWithCoordinatePlane_shouldThrowException_whenAllPlanesDoNotSatisfy() {
        assertThrows(PlaneNotFoundException.class, () -> planeRepository.findPlanesByAngleWithCoordinatePlane(TEST_ANGLE, PlaneCalculator.XY_PLANE));
    }

    @Test
    public void findPlanesByAngleWithCoordinatePlane_shouldReturnListOfPlanes_whenSomePlanesSatisfy() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("9"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("5.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(firstInputPlane, secondInputPlane);
        List<Plane> result = planeRepository.findPlanesByAngleWithCoordinatePlane(TEST_ANGLE, PlaneCalculator.XY_PLANE);
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void findOrthogonalPlanes_shouldThrowException_whenAllPlanesDoNotSatisfy() {
        assertThrows(PlaneNotFoundException.class, planeRepository::findOrthogonalPlanes);
    }

    @Test
    public void findOrthogonalPlanes_shouldReturnListOfPlanes_whenSomePlanesSatisfy() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("5"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("0"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("0"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("5.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(firstInputPlane, thirdInputPlane);

        List<Plane> result = planeRepository.findOrthogonalPlanes();
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void sortPlanesById_shouldReturnRepositorySortedById() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("5"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("0"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("0"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("5.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(firstInputPlane, secondInputPlane, thirdInputPlane);
        planeRepository.sortPlanesByXCoordinateOfFirstDot();
        List<Plane> result = planeRepository.sortPlanesById();
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void sortPlanesByXCoordinateOfFirstDot_shouldReturnRepositorySortedByXCoordinateOfFirstDot() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("5"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("0"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("0"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("6.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(secondInputPlane, firstInputPlane, thirdInputPlane);
        List<Plane> result = planeRepository.sortPlanesByXCoordinateOfFirstDot();
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void sortPlanesByYCoordinateOfFirstDot_shouldReturnRepositorySortedByYCoordinateOfFirstDot() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("5"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("0"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("0"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("6.0"), new BigDecimal("11.0"), new BigDecimal("12.0")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(firstInputPlane, secondInputPlane, thirdInputPlane);
        List<Plane> result = planeRepository.sortPlanesByYCoordinateOfFirstDot();
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void sortPlanesByZCoordinateOfFirstDot_shouldReturnRepositorySortedByZCoordinateOfFirstDot() {
        Plane firstInputPlane = new Plane(new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("3")),
                new Dot(new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("5"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(firstInputPlane);
        Plane secondInputPlane = new Plane(new Dot(new BigDecimal("0"), new BigDecimal("2"), new BigDecimal("4")),
                new Dot(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("6")),
                new Dot(new BigDecimal("0"), new BigDecimal("8"), new BigDecimal("9")));
        planeRepository.create(secondInputPlane);
        Plane thirdInputPlane = new Plane(new Dot(new BigDecimal("6.0"), new BigDecimal("11.0"), new BigDecimal("1")),
                new Dot(new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")),
                new Dot(new BigDecimal("21.0"), new BigDecimal("13.0"), new BigDecimal("18.0")));
        planeRepository.create(thirdInputPlane);
        List<Plane> expectedResult = Arrays.asList(thirdInputPlane, firstInputPlane, secondInputPlane);
        List<Plane> result = planeRepository.sortPlanesByZCoordinateOfFirstDot();
        assertEquals(expectedResult.size(), result.size());
        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }
}
