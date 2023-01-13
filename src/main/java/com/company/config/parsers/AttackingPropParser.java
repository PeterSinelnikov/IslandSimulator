package com.company.config.parsers;

import com.company.animals.AnimalType;
import com.company.config.ConfigParser;

public class AttackingPropParser extends ConfigParser {
    private static AttackingPropParser instance;

    private AttackingPropParser() {
        this.filePath = "src/main/resources/AttackingConfig.csv";
        initializeProperties();
    }

    public static AttackingPropParser getInstance() {
        if (instance == null) {
            instance = new AttackingPropParser();
        }
        return instance;
    }

    public double getAttackingProperty(AnimalType attacker, AnimalType victim){
        String value = properties.get(attacker.index)
                .get(victim.index);
        return Double.parseDouble(value) / 100;
    }

}
