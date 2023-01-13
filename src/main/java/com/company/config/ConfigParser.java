package com.company.config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class ConfigParser {
    protected String filePath;
    protected List<CSVRecord> properties;

    protected void initializeProperties() {
        try(Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            properties = CSVFormat.DEFAULT.parse(reader).getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
