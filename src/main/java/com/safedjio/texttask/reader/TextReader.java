package com.safedjio.texttask.reader;
import com.safedjio.texttask.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {

    private static final Logger logger = LogManager.getLogger();

    public String readAll(String filePath) {
        if (filePath == null) {
            throw new TextException("File path cannot be null");
        }
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new TextException("File not found: " + filePath);
        }

        try {
            String content = Files.readString(path);
            logger.info("File read successfully from: {}", filePath);
            return content;
        } catch (IOException e) {
            logger.error("Error reading file", e);
            throw new TextException("Error reading file", e);
        }
    }
}