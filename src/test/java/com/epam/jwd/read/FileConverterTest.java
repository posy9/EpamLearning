package com.epam.jwd.read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FileConverterTest {

    private static final String PATH_TO_FILE_WITH_CORRECT_VALUES = "D:\\epam\\task2\\valid.txt";
    private static final String PATH_TO_FILE_WITH_INCORRECT_VALUES = "D:\\epam\\task2\\allInvalid.txt";
    private static final String PATH_TO_FILE_WITH_SOME_INCORRECT_VALUES = "D:\\epam\\task2\\someValid.txt";

    private FileConverter fileConverter;

    @BeforeEach
    void setUp() {
        fileConverter = new FileConverter();
    }


    @Test
    public void readFileAndParseToDouble_shouldReturnListOfCoordinatesWithCorrectSize_whenAllValuesAreValid() {

        List<List<Double>> result = fileConverter.readFileAndParseToDouble(PATH_TO_FILE_WITH_CORRECT_VALUES);
        assertEquals(3, result.size());
    }

    @Test
    public void readFileAndParseToDouble_shouldReturnEmptyList_whenAllValuesAreNotValid() {

        List<List<Double>> result = fileConverter.readFileAndParseToDouble(PATH_TO_FILE_WITH_INCORRECT_VALUES);
        assertEquals(0, result.size());
    }

    @Test
    public void readFileAndParseToDouble_shouldReturnListOfCoordinatesWithCorrectSize_whenSomeValuesAreNotValid() {

        List<List<Double>> result = fileConverter.readFileAndParseToDouble(PATH_TO_FILE_WITH_SOME_INCORRECT_VALUES);
        assertEquals(3, result.size());
    }
}
