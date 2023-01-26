package com.company.config.properties;

import com.company.animals.AnimalType;
import com.company.config.parsers.AttackingPropertiesParser;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class AttackingProperties {
    private static final List<CSVRecord> PROPERTIES;

    static {
        AttackingPropertiesParser parser = new AttackingPropertiesParser();
        PROPERTIES = parser.getProperties();
    }

    public static double getAttackingProperty(AnimalType attacker, AnimalType victim){
        String value = PROPERTIES.get(attacker.index)
                .get(victim.index);
        return Double.parseDouble(value) / 100;
    }
}
