package com.epam.jwd.read;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileParser {



    private static final Logger LOG = LogManager.getLogger(FileParser.class);

    public List<List<BigDecimal>> readFileAndParseToBigDecimal(String path_to_file) {
        Path path = Paths.get(path_to_file);

        List<List<BigDecimal>> allLists = new ArrayList<>();
        try {

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                line = line.replaceAll("\\s+", " ");
                List<BigDecimal> currentList = new ArrayList<>();
                boolean hasError = false;
                for (String element : line.split(" ")) {
                    try {
                        currentList.add(new BigDecimal(element));
                        LOG.trace("Element {} successfully parsed", element);

                    } catch (NumberFormatException e) {
                        LOG.error("Error parsing element {}", element);
                        hasError = true;
                        break;
                    }
                }
                if (!hasError) {
                    allLists.add(currentList);
                }

            }

        } catch (IOException e) {
            LOG.error("Error reading file {}", path_to_file);
        }

        return allLists;
    }

}