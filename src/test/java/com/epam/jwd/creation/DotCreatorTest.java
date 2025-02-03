package com.epam.jwd.creation;

import com.epam.jwd.exception.IllegalDotCoordinatesException;
import com.epam.jwd.model.Dot;
import com.epam.jwd.read.FileParser;
import com.epam.jwd.validation.DotValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
    private FileParser mockFileConverter;

    @InjectMocks
    private DotCreator dotCreator;

    @Test
    public void createDots_shouldReturnListOfListOfDotsOfCorrectSize_whenAllCoordinatesAreValid()
            throws IllegalDotCoordinatesException {
        List<List<BigDecimal>> mockCoordinatesList = Arrays.asList(
                Arrays.asList(
                        new BigDecimal("7"), new BigDecimal("4"), new BigDecimal("5"),
                        new BigDecimal("11"), new BigDecimal("16"), new BigDecimal("23"),
                        new BigDecimal("42"), new BigDecimal("34"), new BigDecimal("5")
                ),
                Arrays.asList(
                        new BigDecimal("1"), new BigDecimal("52"), new BigDecimal("22"),
                        new BigDecimal("13"), new BigDecimal("111"), new BigDecimal("34"),
                        new BigDecimal("54"), new BigDecimal("33"), new BigDecimal("18")
                )
        );
        lenient().when(mockFileConverter.readFileAndParseToBigDecimal(anyString())).thenReturn(mockCoordinatesList);
        lenient().doNothing().when(mockDotValidator).isValidDot(anyList());
        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);
        assertEquals(mockCoordinatesList.size(), result.size());
    }

    @Test
    public void createDots_shouldReturnListOfDotsWithCorrectCoordinates_whenAllCoordinatesAreValid()
            throws IllegalDotCoordinatesException {


        List<List<BigDecimal>> mockCoordinatesList = List.of(Arrays.asList(
                new BigDecimal("7"), new BigDecimal("4"), new BigDecimal("5"),
                new BigDecimal("11"), new BigDecimal("16"), new BigDecimal("23"),
                new BigDecimal("42"), new BigDecimal("34"), new BigDecimal("5")
        ));

        lenient().when(mockFileConverter.readFileAndParseToBigDecimal(anyString())).thenReturn(mockCoordinatesList);
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

        List<List<BigDecimal>> mockCoordinatesList = Arrays.asList(
                Arrays.asList(
                        new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"),
                        new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6"),
                        new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9")
                ),
                Arrays.asList(
                        new BigDecimal("10"), new BigDecimal("11"), new BigDecimal("12"),
                        new BigDecimal("13"), new BigDecimal("14"), new BigDecimal("15"),
                        new BigDecimal("16"), new BigDecimal("17")
                )
        );

        lenient().when(mockFileConverter.readFileAndParseToBigDecimal(anyString())).thenReturn(mockCoordinatesList);

        lenient().doNothing().when(mockDotValidator).isValidDot(eq(mockCoordinatesList.get(0)));
        lenient().doThrow(new IllegalDotCoordinatesException("Invalid coordinates"))
                .when(mockDotValidator).isValidDot(eq(mockCoordinatesList.get(1)));

        List<List<Dot>> result = dotCreator.createDots(mockFileConverter);

        assertEquals(1, result.size());
    }

    @ParameterizedTest
    @MethodSource("mockDotListAndCorrectSizeProvider")
    void createDots_shouldReturnEmptyList_whenAllCoordinatesAreNotValid(List<List<BigDecimal>> mockDotList, int expectedAnswer) throws IllegalDotCoordinatesException {

        lenient().when(mockFileConverter.readFileAndParseToBigDecimal(anyString())).thenReturn(mockDotList);

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
