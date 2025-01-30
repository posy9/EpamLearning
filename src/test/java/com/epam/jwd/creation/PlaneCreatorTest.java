package com.epam.jwd.creation;


import com.epam.jwd.model.Dot;
import com.epam.jwd.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class PlaneCreatorTest {

    private PlaneCreator planeCreator;

    @BeforeEach
    public void setUp() {
         planeCreator = new PlaneCreator();
    }



    @Test
    public void createPlanes_shouldReturnListOfPlanes_whenAllDotesAreValid(){

        List<List<Dot>> mockDotList = Arrays.asList(
                Arrays.asList(new Dot(10.0, 11.0, 12.0),new Dot(13.0, 14.0, 15.0),
                        new Dot(21.0, 13.0, 18.0)),
                Arrays.asList(new Dot(10.0, 11.0, 12.0),new Dot(13.0, 14.0, 15.0),
                        new Dot(21.0, 13.0, 18.0)),
                Arrays.asList(new Dot(10.0, 11.0, 12.0),new Dot(13.0, 14.0, 15.0),
                        new Dot(21.0, 13.0, 18.0))
        );
        List<Plane> result = planeCreator.createPlanes(mockDotList);
        assertEquals(3, result.size());

    }

    @Test
    public void createPlanes_shouldReturnListOfPlanesWithCorrectDots_whenAllDotsAreValid(){

        List<List<Dot>> mockDotList = List.of(
                Arrays.asList(new Dot(10.0, 11.0, 12.0), new Dot(13.0, 14.0, 15.0),
                        new Dot(21.0, 13.0, 18.0))
        );
        List<Plane> result = planeCreator.createPlanes(mockDotList);

        assertEquals(mockDotList.getFirst().getFirst(), result.getFirst().getA());
        assertEquals(mockDotList.getFirst().get(1), result.getFirst().getB());
        assertEquals(mockDotList.getFirst().get(2), result.getFirst().getC());

    }

    @Test
    public void createPlanes_shouldReturnListOfPlanesOfCorrectSize_whenSomeDotsAreValid(){

        List<List<Dot>> mockDotList = Arrays.asList(
                Arrays.asList(new Dot(10.0, 11.0, 12.0), new Dot(13.0, 14.0, 15.0),
                        new Dot(21.0, 13.0, 18.0)),
                Arrays.asList(new Dot(1.0, 2.0, 3.0), new Dot(2.0, 4.0, 6.0),
                        new Dot(3.0, 6.0, 9.0))
        );

        List<Plane> result = planeCreator.createPlanes(mockDotList);

        assertEquals(1, result.size());
    }

    @ParameterizedTest
    @MethodSource("mockIncorrectDotsListAndCorrectSizeProvider")
    public void createPlanes_shouldReturnEmptyListOfPlanes_whenAllDotsAreNotValid(List<List<Dot>> mockDotList,int expectedAnswer){

        List<Plane> result = planeCreator.createPlanes(mockDotList);

        assertEquals(expectedAnswer, result.size());
    }


    private static Stream<Arguments> mockIncorrectDotsListAndCorrectSizeProvider() {
        return Stream.of(
                arguments(Arrays.asList(
                        Arrays.asList(new Dot(1.0, 2.0, 3.0), new Dot(2.0, 4.0, 6.0),new Dot(3.0, 6.0, 9.0)),
                        Arrays.asList(new Dot(3.0, 4.0, 5.0), new Dot(6.0, 8.0, 10.0),new Dot(9.0, 12.0, 15.0))),0),
                arguments(List.of(),0)
        );
    }




}




