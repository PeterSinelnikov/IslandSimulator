package com.company.animals;

import com.company.animals.animalFactory.Factory;
import com.company.config.properties.AnimalProperties;
import com.company.island.Cell;
import com.company.island.Island;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal {
    @Getter
    @Setter
    private double weight;
    private boolean isAlive = true;
    @Getter
    @Setter
    private int corpseDayCounter;
    @Getter
    private Cell cell;
    private boolean hasOffspring;
    protected boolean isFemale;
    @Getter
    @Setter
    private Animal offenderName;
    @Getter
    @Setter
    private int birthDay;

    public Animal(Cell cell) {
        this.cell = cell;
        this.cell.getAnimals().add(this);
        initializeFields();
    }

    private void initializeFields() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        weight = AnimalProperties.getWeight(getType());
        isFemale = random.nextBoolean();
    }

    public abstract void eat();

    public void move() {
        Cell currentCell = this.cell;
        List<Cell> availableCellsToGo = getAvailableCellsToGo();
        Cell chosenCell = chooseWhereToGo(availableCellsToGo);
        if (chosenCell != currentCell) {
            this.cell = chosenCell;
            synchronized (currentCell.getAnimals()) {
                currentCell.getAnimals().remove(this);
            }
            synchronized (this.cell.getAnimals()) {
                this.cell.getAnimals().add(this);
            }
        }
    }

    public void reproduce() {
        if (this.isFemale) {
            return;
        }
        if (this.hasOffspring) {
            return;
        }
        List<Animal> animalsReadyToReproduce = this.cell.getAnimals().stream()
                .filter(animal -> animal.isAlive)
                .filter(animal -> animal.getType() == this.getType())
                .filter(animal -> animal.isFemale)
                .toList();
        if (animalsReadyToReproduce.size() > 0) {
            Factory.getInstance().createAnimal(this.getType(), this.cell);
            this.hasOffspring = true;
            animalsReadyToReproduce.stream().findAny().get().hasOffspring = true;
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
        for (int i = -getSpeed(); i <= getSpeed(); i++) {
            for (int j = -getSpeed(); j <= getSpeed(); j++) {
                if (Math.abs(i) + Math.abs(j) > getSpeed()) {
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

    public int getSpeed() {
        return AnimalProperties.getSpeed(this.getType());
    }

    public abstract AnimalType getType();

    public void die() {
        isAlive = false;
    }

    public boolean isDead() {
        return !isAlive;
    }
}
