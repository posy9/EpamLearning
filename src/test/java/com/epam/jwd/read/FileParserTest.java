package com.epam.jwd.read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FileParserTest {

    private static final String PATH_TO_FILE_WITH_CORRECT_VALUES = "valid.txt";
    private static final String PATH_TO_FILE_WITH_INCORRECT_VALUES = "allInvalid.txt";
    private static final String PATH_TO_FILE_WITH_SOME_INCORRECT_VALUES = "someValid.txt";

    private FileParser fileConverter;

    @BeforeEach
    void setUp() {
        fileConverter = new FileParser();
    }


    @Test
    public void readFileAndParseToDouble_shouldReturnListOfCoordinatesWithCorrectSize_whenAllValuesAreValid() {

        List<List<BigDecimal>> result = fileConverter.readFileAndParseToBigDecimal(PATH_TO_FILE_WITH_CORRECT_VALUES);
        assertEquals(3, result.size());
    }

    @Test
    public void readFileAndParseToDouble_shouldReturnEmptyList_whenAllValuesAreNotValid() {

        List<List<BigDecimal>> result = fileConverter.readFileAndParseToBigDecimal(PATH_TO_FILE_WITH_INCORRECT_VALUES);
        assertEquals(0, result.size());
    }

    @Test
    public void readFileAndParseToDouble_shouldReturnListOfCoordinatesWithCorrectSize_whenSomeValuesAreNotValid() {

        List<List<BigDecimal>> result = fileConverter.readFileAndParseToBigDecimal(PATH_TO_FILE_WITH_SOME_INCORRECT_VALUES);
        assertEquals(3, result.size());
    }
}
