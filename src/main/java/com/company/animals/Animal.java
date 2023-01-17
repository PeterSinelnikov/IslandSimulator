package com.company.animals;

import com.company.animals.AnimalFactory.Factory;
import com.company.config.parsers.AnimalFieldsParser;
import com.company.island.Cell;
import com.company.island.Island;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal {
    @Getter
    private final AnimalType type;
    @Getter
    @Setter
    private double weight;
    @Getter
    private int maxAmountPerCell;
    private int speed;
    private boolean isAlive = true;
    @Getter
    @Setter
    private int corpseLifeCounter;
    @Getter
    private double requiredAmountOfFood;
    @Getter
    private double maxDailyWeightLoss;
    @Getter
    private Cell cell;
    private boolean hasOffspring;
    private boolean isMale;
    @Getter
    @Setter
    private Animal offenderName;
    @Getter
    @Setter
    private int birthDay;

    public Animal(Cell cell, AnimalType type) {
        this.cell = cell;
        this.type = type;
        this.cell.getAnimals().add(this);
        initializeFields();
    }

    private void initializeFields() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        weight = AnimalFieldsParser.getInstance().getWeight(type);
        maxAmountPerCell = AnimalFieldsParser.getInstance().getMaxAmountPerCell(type);
        speed = AnimalFieldsParser.getInstance().getSpeed(type);
        requiredAmountOfFood = AnimalFieldsParser.getInstance().getRequiredAmountOfFood(type);
        maxDailyWeightLoss = AnimalFieldsParser.getInstance().getMaxDailyWeightLoss(type);
        isMale = random.nextBoolean();
    }

    public abstract void eat();

    public void move() {
        Cell currentCell = this.cell;
        List<Cell> availableCellsToGo = getAvailableCellsToGo();
        this.cell = chooseWhereToGo(availableCellsToGo);
        if (this.cell != currentCell) {
            currentCell.getAnimals().remove(this);
            this.cell.getAnimals().add(this);
        }
    }

    public void reproduce() {
        if (isFemale()) {
            return;
        }
        if (this.hasOffspring) {
            return;
        }
        List<Animal> animalsReadyToReproduce = this.cell.getAnimals().stream()
                .filter(animal -> animal.isAlive)
                .filter(animal -> animal.getType() == this.type)
                .filter(Animal::isFemale)
                .toList();
        if (animalsReadyToReproduce.size() > 0) {
            Factory.getInstance().createAnimal(this.type,this.cell);
            this.hasOffspring = true;
            animalsReadyToReproduce.get(0).hasOffspring = true;
        }
    }

    protected abstract Cell chooseWhereToGo(List<Cell> availableCellsToGo);

    /**
     * This method calculates which cell are available for the animal with regard to its current location, island boundaries and animal's speed.
     *
     * @return List of available cells where this animal can possible go in one stretch of time.
     */
    private List<Cell> getAvailableCellsToGo() {
        Cell[][] allCells = Island.getCells();
        List<Cell> availableCellsToGo = new ArrayList<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                if (Math.abs(i) + Math.abs(j) > 4) {
                    continue;
                }
                int currentX = this.cell.getX() + i;
                int currentY = this.cell.getY() + j;
                if (currentX >= 0
                        && currentX < Island.getXAmountOfCells()
                        && currentY >= 0
                        && currentY < Island.getYAmountOfCells()) {
                    Cell current = allCells[currentX][currentY];
                    availableCellsToGo.add(current);
                }
            }
        }
        return availableCellsToGo;
    }

    public void die() {
        isAlive = false;
    }

    public boolean isDead() {
        return !isAlive;
    }

    public boolean isFemale() {
        return !isMale;
    }
}
