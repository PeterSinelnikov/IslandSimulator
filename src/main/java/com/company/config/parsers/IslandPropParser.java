package com.company.config.parsers;


import com.company.animals.AnimalType;
import com.company.config.ConfigParser;

public class IslandPropParser extends ConfigParser {
    private static IslandPropParser instance;
    private final int columnNumber = 1;

    private IslandPropParser() {
        this.filePath = "src/main/resources/IslandProperties.csv";
        initializeProperties();
    }

    public static IslandPropParser getInstance() {
        if (instance == null) {
            instance = new IslandPropParser();
        }
        return instance;
    }

    public int getXAmountOfCells() {
        int xAmountOfCellsIndex = 1;
        String value = properties.get(xAmountOfCellsIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public int getYAmountOfCells() {
        int yAmountOfCellsIndex = 2;
        String value = properties.get(yAmountOfCellsIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public int getDayDuration() {
        int dayDurationIndex = 3;
        String value = properties.get(dayDurationIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public double getPlantsGrowthFactor() {
        int plantsGrowthFactor = 4;
        String value = properties.get(plantsGrowthFactor)
                .get(columnNumber);
        return Double.parseDouble(value);
    }
}
