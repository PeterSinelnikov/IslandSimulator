package com.company.config.parsers;


import com.company.config.ConfigParser;

public class IslandPropParser extends ConfigParser {

    public IslandPropParser() {
        this.filePath = "src/main/resources/IslandProperties.csv";
        initializeProperties();
    }
}
