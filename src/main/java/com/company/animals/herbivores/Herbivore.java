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
                .filter(cell1 -> cell1.countType(this.getType()) < getMaxAmountPerCell())
                .filter(cell2 -> !cell2.containsCarnivore())
                .max(Comparator.comparingInt(Cell::getPlants))
                .orElseGet(this::getCell);
    }

    @Override
    public void eat() {
        int currentCellPlants = this.getCell().getPlants();
        if (currentCellPlants > getRequiredAmountOfFood()) {
            this.getCell().setPlants((int) (currentCellPlants - getRequiredAmountOfFood()));
            this.setWeight(AnimalFieldsParser.getInstance().getWeight(this.getType()));
        } else {
            double weightLoss = (getRequiredAmountOfFood() - currentCellPlants) / getRequiredAmountOfFood() * getMaxDailyWeightLoss();
            this.setWeight(getWeight() - weightLoss);
            this.getCell().setPlants(0);
        }
    }
}
