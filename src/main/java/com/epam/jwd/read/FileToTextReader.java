package com.epam.jwd.read;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.*;
import java.io.IOException;

public class FileToTextReader {

    private static final Logger LOG = LogManager.getLogger(FileToTextReader.class);
    private static FileToTextReader instance;

    private FileToTextReader() {}

    public static FileToTextReader getInstance() {
        if (instance == null) {
            instance = new FileToTextReader();
        }
        return instance;
    }

    public String readFileToText(String path) {
        Path filePath = Paths.get(path);
        String text = "";
        try {
            text = Files.readString(filePath);
        } catch (IOException e) {
           LOG.error("Error reading file {}", path);
        }
        return text;
    }
}
