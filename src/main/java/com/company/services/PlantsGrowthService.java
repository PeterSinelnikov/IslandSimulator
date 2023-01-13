package com.company.services;

import com.company.config.parsers.IslandPropParser;
import com.company.island.Cell;
import com.company.island.Island;

import java.util.Arrays;

public class PlantsGrowthService implements Runnable {

    protected static final double PLANTS_GROWTH_MULTIPLIER = IslandPropParser.getInstance().getPlantsGrowthFactor();

    @Override
    public void run() {
        Arrays.stream(Island.getCells())
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getPlants()
                        < Math.floor(Cell.MAX_AMOUNT_OF_PLANTS / PLANTS_GROWTH_MULTIPLIER))
                .forEach(cell -> cell.setPlants((int) (cell.getPlants() * PLANTS_GROWTH_MULTIPLIER)));
    }
}
