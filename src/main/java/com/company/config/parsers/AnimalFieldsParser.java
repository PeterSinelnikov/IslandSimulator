package com.company.config.parsers;

import com.company.animals.AnimalType;
import com.company.config.ConfigParser;

public class AnimalFieldsParser extends ConfigParser {

    private static AnimalFieldsParser instance;

    private AnimalFieldsParser() {
        this.filePath = "src/main/resources/AnimalFieldsConfig.csv";
        initializeProperties();
    }

    public static AnimalFieldsParser getInstance() {
        if (instance == null) {
            instance = new AnimalFieldsParser();
        }
        return instance;
    }
    public double getWeight(AnimalType animal) {
        int weightIndex = 1;
        String value = properties.get(animal.index)
                .get(weightIndex);
        return Double.parseDouble(value);
    }

    public int getMaxAmountPerCell(AnimalType animal) {
        int maxAmPerCellIndex = 2;
        String value = properties.get(animal.index)
                .get(maxAmPerCellIndex);
        return Integer.parseInt(value);
    }

    public int getSpeed(AnimalType animal) {
        int speedIndex = 3;
        String value = properties.get(animal.index)
                .get(speedIndex);
        return Integer.parseInt(value);
    }

    public double getRequiredAmountOfFood(AnimalType animal) {
        int requiredAmOfFoodIndex = 4;
        String value = properties.get(animal.index)
                .get(requiredAmOfFoodIndex);
        return Double.parseDouble(value);
    }

    public int getInitialQuantity(AnimalType animal) {
        int initialQuantityIndex = 5;
        String value = properties.get(animal.index)
                .get(initialQuantityIndex);
        return Integer.parseInt(value);
    }

    public double getMaxDailyWeightLoss(AnimalType animal) {
        int maxDailyWeightLoss = 6;
        String value = properties.get(animal.index)
                .get(maxDailyWeightLoss);
        return Double.parseDouble(value);
    }
}
