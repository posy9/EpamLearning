package com.epam.jwd.read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileToTextReaderTest {

    private static final String PATH_TO_FILE = "testText.txt";
    private static final String WRONG_PATH_TO_FILE = "Path.txt";

    private FileToTextReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = FileToTextReader.getInstance();
    }

    @Test
    public void readFileToText_shouldReturnText_whenPathExists() {
        String expectedResult = """
                    \tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularized in the 5(1&2&(3|(4&(^56&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    \tIt is a long-established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
                    \tIt is a (~5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.
                    \tBye.""";
        String result = fileReader.readFileToText(PATH_TO_FILE);
        assertTrue(equalsIgnoreLineSeparators(expectedResult, result));
    }

    @Test
    public void readFileToText_shouldReturnEmptyString_whenPathDoesNotExists() {
        String expectedResult = "";
        String result = fileReader.readFileToText(WRONG_PATH_TO_FILE);
        assertEquals(expectedResult,result);
    }

    private boolean equalsIgnoreLineSeparators(String text1, String text2) {
        String normalizedText1 = text1.replaceAll("\\r\\n|\\r|\\n", "");
        String normalizedText2 = text2.replaceAll("\\r\\n|\\r|\\n", "");
        return normalizedText1.equals(normalizedText2);
    }
}
