package com.company.config.properties;

import com.company.config.parsers.IslandPropParser;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class IslandProperties {

    private static final List<CSVRecord> PROPERTIES;
    private static final int columnNumber = 1;

    static {
        IslandPropParser parser = new IslandPropParser();
        PROPERTIES = parser.getProperties();
    }

    public static int getXAmountOfCells() {
        int xAmountOfCellsIndex = 1;
        String value = PROPERTIES.get(xAmountOfCellsIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public static int getYAmountOfCells() {
        int yAmountOfCellsIndex = 2;
        String value = PROPERTIES.get(yAmountOfCellsIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public static int getDayDuration() {
        int dayDurationIndex = 3;
        String value = PROPERTIES.get(dayDurationIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }

    public static double getPlantsGrowthFactor() {
        int plantsGrowthFactorIndex = 4;
        String value = PROPERTIES.get(plantsGrowthFactorIndex)
                .get(columnNumber);
        return Double.parseDouble(value);
    }

    public static int getMaxQuantityOfPlantsPerCell() {
        int maxQuantityOfPlantsPerCellIndex = 5;
        String value = PROPERTIES.get(maxQuantityOfPlantsPerCellIndex)
                .get(columnNumber);
        return Integer.parseInt(value);
    }
}
