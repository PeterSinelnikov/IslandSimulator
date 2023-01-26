package com.company.animals.herbivores;

import com.company.animals.Animal;
import com.company.config.properties.AnimalProperties;
import com.company.island.Cell;

import java.util.Comparator;
import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell) {
        super(cell);
    }

    @Override
    protected Cell chooseWhereToGo(List<Cell> availableCellsToGo) {
        return availableCellsToGo.stream()
                .filter(cell1 -> cell1.countType(this.getType()) < AnimalProperties.getMaxAmountPerCell(this.getType()))
                .filter(cell2 -> !cell2.containsCarnivore())
                .max(Comparator.comparingInt(Cell::getPlants))
                .orElseGet(this::getCell);
    }

    @Override
    public void eat() {
        double requiredAmountOfFood = AnimalProperties.getRequiredAmountOfFood(this.getType());
        int currentCellPlants = this.getCell().getPlants();
        if (currentCellPlants > requiredAmountOfFood) {
            this.getCell().setPlants((int) (currentCellPlants - requiredAmountOfFood));
            this.setWeight(AnimalProperties.getWeight(this.getType()));
        } else {
            double weightLoss = (requiredAmountOfFood - currentCellPlants)
                    / requiredAmountOfFood * AnimalProperties.getMaxDailyWeightLoss(this.getType());
            this.setWeight(getWeight() - weightLoss);
            this.getCell().setPlants(0);
        }
    }
}
