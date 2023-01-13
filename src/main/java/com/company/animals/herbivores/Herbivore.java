package com.company.animals.herbivores;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.config.parsers.AnimalFieldsParser;
import com.company.island.Cell;

import java.util.Comparator;
import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    protected Cell chooseWhereToGo(List<Cell> availableCellsToGo) {
        return availableCellsToGo.stream()
                .filter(cell1 -> cell1.countType(type) < maxAmountPerCell)
                .filter(cell2 -> !cell2.containsCarnivore())
                .max(Comparator.comparingInt(Cell::getPlants))
                .orElseGet(() -> this.cell);
    }

    @Override
    public void eat() {
        int currentCellPlants = this.getCell().getPlants();
        if (currentCellPlants > requiredAmountOfFood) {
            this.cell.setPlants((int) (currentCellPlants - requiredAmountOfFood));
            this.weight = AnimalFieldsParser.getInstance().getWeight(this.type);
        } else {
            double weightLoss = (requiredAmountOfFood - currentCellPlants) / requiredAmountOfFood * maxDailyWeightLoss;
            this.weight = weight - weightLoss;
            this.cell.setPlants(0);
        }
    }
}
