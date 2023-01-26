package com.company.config.parsers;

import com.company.config.ConfigParser;

public class AnimalFieldsParser extends ConfigParser {

    public AnimalFieldsParser() {
        this.filePath = "src/main/resources/AnimalFieldsConfig.csv";
        initializeProperties();
    }
}
