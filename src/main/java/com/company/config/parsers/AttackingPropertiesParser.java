package com.company.config.parsers;

import com.company.config.ConfigParser;

public class AttackingPropertiesParser extends ConfigParser {

    public AttackingPropertiesParser() {
        this.filePath = "src/main/resources/AttackingConfig.csv";
        initializeProperties();
    }
}
