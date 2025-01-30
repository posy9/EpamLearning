package com.epam.jwd.creation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.read.FileConverter;
import com.epam.jwd.validation.DotValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DotCreatorTest {

    @Mock
    private DotValidator mockDotValidator;
    @Mock
    private FileConverter mockFileConverter;

    @InjectMocks
    private DotCreator dotCreator;

    @Test
    public void createDots_shouldReturnListOfListOfDotsOfCorrectSize_whenAllCoordinatesAreValid()
            throws IllegalDotCoordinatesException {
        List<List<Double>> mockCoordinatesList = Arrays.asList(
                Arrays.asList(7.0, 4.0, 5.0, 11.0, 16.0, 23.0, 42.0, 34.0, 5.0),
                Arrays.asList(1.0, 52.0, 22.0, 13.0, 111.0, 34.0, 54.0, 33.0, 18.0)
        );
        lenient().when(mockFileConverter.readFileAndParseToDouble(anyString())).thenReturn(mockCoordinatesList);
        lenient().doNothing().when(mockDotValidator).isValidDot(anyList());
        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);
        assertEquals(mockCoordinatesList.size(), result.size());
    }

    @Test
    public void createDots_shouldReturnListOfDotsWithCorrectCoordinates_whenAllCoordinatesAreValid()
            throws IllegalDotCoordinatesException {

        List<List<Double>> mockCoordinatesList = List.of(Arrays.asList(7.0, 4.0, 5.0, 11.0, 16.0, 23.0, 42.0, 34.0, 5.0));

        lenient().when(mockFileConverter.readFileAndParseToDouble(anyString())).thenReturn(mockCoordinatesList);
        lenient().doNothing().when(mockDotValidator).isValidDot(anyList());
        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);

        List<Dot> firstDots = result.getFirst();

        assertEquals(mockCoordinatesList.getFirst().getFirst(), firstDots.getFirst().getX());
        assertEquals(mockCoordinatesList.getFirst().get(1), firstDots.getFirst().getY());
        assertEquals(mockCoordinatesList.getFirst().get(2), firstDots.getFirst().getZ());

        assertEquals(mockCoordinatesList.getFirst().get(3), firstDots.get(1).getX());
        assertEquals(mockCoordinatesList.getFirst().get(4), firstDots.get(1).getY());
        assertEquals(mockCoordinatesList.getFirst().get(5), firstDots.get(1).getZ());

        assertEquals(mockCoordinatesList.getFirst().get(6), firstDots.get(2).getX());
        assertEquals(mockCoordinatesList.getFirst().get(7), firstDots.get(2).getY());
        assertEquals(mockCoordinatesList.getFirst().get(8), firstDots.get(2).getZ());

    }

    @Test
    void createDots_shouldReturnListOfListOfDotsOfCorrectSize_whenSomeCoordinatesAreValid() throws IllegalDotCoordinatesException {
        // Тестируем случай, когда координаты точки невалидны
        List<List<Double>> mockCoordinatesList = Arrays.asList(
                Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0),
                Arrays.asList(10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0)
        );

        lenient().when(mockFileConverter.readFileAndParseToDouble(anyString())).thenReturn(mockCoordinatesList);

        lenient().doNothing().when(mockDotValidator).isValidDot(eq(mockCoordinatesList.get(0)));
        lenient().doThrow(new IllegalDotCoordinatesException("Invalid coordinates"))
                .when(mockDotValidator).isValidDot(eq(mockCoordinatesList.get(1)));

        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);

        assertEquals(1, result.size());
    }

    @ParameterizedTest
    @MethodSource("mockDotListAndCorrectSizeProvider")
    void createDots_shouldReturnEmptyList_whenAllCoordinatesAreNotValid(List<List<Double>> mockDotList, int expectedAnswer) throws IllegalDotCoordinatesException {

        lenient().when(mockFileConverter.readFileAndParseToDouble(anyString())).thenReturn(mockDotList);

        lenient().doThrow(new IllegalDotCoordinatesException("Invalid coordinates"))
                .when(mockDotValidator).isValidDot(anyList());

        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);

        assertEquals(expectedAnswer, result.size());
    }

    private static Stream<Arguments> mockDotListAndCorrectSizeProvider() {
        return Stream.of(
                arguments(Arrays.asList(
                        Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 9.0),
                        Arrays.asList(10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0)), 0),
                arguments(List.of(), 0)
        );
    }


}
