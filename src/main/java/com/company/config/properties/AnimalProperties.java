package com.company.config.properties;

import com.company.animals.AnimalType;
import com.company.config.parsers.AnimalFieldsParser;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class AnimalProperties {
    private static final List<CSVRecord> PROPERTIES;

    static {
        AnimalFieldsParser parser = new AnimalFieldsParser();
        PROPERTIES = parser.getProperties();
    }

    public static double getWeight(AnimalType animal) {
        int weightIndex = 1;
        String value = PROPERTIES.get(animal.index)
                .get(weightIndex);
        return Double.parseDouble(value);
    }

    public static int getMaxAmountPerCell(AnimalType animal) {
        int maxAmPerCellIndex = 2;
        String value = PROPERTIES.get(animal.index)
                .get(maxAmPerCellIndex);
        return Integer.parseInt(value);
    }

    public static int getSpeed(AnimalType animal) {
        int speedIndex = 3;
        String value = PROPERTIES.get(animal.index)
                .get(speedIndex);
        return Integer.parseInt(value);
    }

    public static double getRequiredAmountOfFood(AnimalType animal) {
        int requiredAmOfFoodIndex = 4;
        String value = PROPERTIES.get(animal.index)
                .get(requiredAmOfFoodIndex);
        return Double.parseDouble(value);
    }

    public static int getInitialQuantity(AnimalType animal) {
        int initialQuantityIndex = 5;
        String value = PROPERTIES.get(animal.index)
                .get(initialQuantityIndex);
        return Integer.parseInt(value);
    }

    public static double getMaxDailyWeightLoss(AnimalType animal) {
        int maxDailyWeightLoss = 6;
        String value = PROPERTIES.get(animal.index)
                .get(maxDailyWeightLoss);
        return Double.parseDouble(value);
    }
}
