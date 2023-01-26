package com.company.engine;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.config.properties.AnimalProperties;
import com.company.island.Island;

public class AnimalDaySimulation implements Runnable {

    public static final double MAX_WEIGHT_LOSS = 0.70;

    @Override
    public void run() {
        Initializer.dayCounter++;
        if (Island.getAllAnimals().size() < 2) {
            IslandSimulationService.stopSimulation();
        }
        Island.getAllAnimals().forEach(this::dealWithCorpses);
        dealWithCaterpillars();
        runAnimalLifeCycle();
        setBirthdaysForNewAnimals();
        StatsPrinter.printDayStats();
    }

    private void setBirthdaysForNewAnimals() {
        Island.getAllAnimals().stream()
                .filter(animal -> animal.getBirthDay() == 0)
                .forEach(animal -> animal.setBirthDay(Initializer.dayCounter));
    }

    private void dealWithCorpses(Animal animal) {
        if (animal.isDead()) {
            animal.setCorpseDayCounter(animal.getCorpseDayCounter() + 1);
        }
        if (animal.getCorpseDayCounter() > 1) {
            eraseExpiredAnimalCorpse(animal);
        }
    }

    private void dealWithCaterpillars() {
        Island.getAllAnimals().stream()
                .filter(animal -> animal.getType() == AnimalType.CATERPILLAR)
                .filter(animal -> animal.getCell().getPlants() == 0)
                .forEach(Animal::die);
    }

    private void eraseExpiredAnimalCorpse(Animal animal) {
        animal.getCell().getAnimals().remove(animal);
    }

    private void runAnimalLifeCycle() {
        Island.getAllAnimals().stream()
                .filter(this::checkLifeCondition)
                .forEach(animal -> {
                    animal.eat();
                    animal.move();
                    animal.reproduce();
                });
    }

    private boolean checkLifeCondition(Animal animal) {
        double configWeight = AnimalProperties.getWeight(animal.getType());
        if (animal.getWeight() < (configWeight * MAX_WEIGHT_LOSS)
                && !animal.isDead()) {
            animal.die();
        }
        return !animal.isDead();
    }
}
