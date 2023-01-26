package com.company.engine;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.config.properties.AnimalProperties;
import com.company.island.Island;

import java.util.List;

public class AnimalDaySimulation implements Runnable {

    public static final double MAX_WEIGHT_LOSS = 0.70;

    @Override
    public void run() {
        Initializer.dayCounter++;
        Island.getAllAnimals().forEach(this::dealWithCorpses);
        List<Animal> allAnimals = Island.getAllAnimals();
        if (allAnimals.size() < 2) {
            IslandSimulationService.stopSimulation();
        }
        dealWithCaterpillars(allAnimals);
        runAnimalLifeCycle(allAnimals);
        setBirthdaysForNewAnimals(allAnimals);
        StatsPrinter.printDayStats();
    }

    private void dealWithCorpses(Animal animal) {
        if (animal.isDead()) {
            animal.setCorpseDayCounter(animal.getCorpseDayCounter() + 1);
        }
        if (animal.getCorpseDayCounter() > 1) {
            eraseExpiredAnimalCorpse(animal);
        }
    }

    private void eraseExpiredAnimalCorpse(Animal animal) {
        animal.getCell().getAnimals().remove(animal);
    }

    private void dealWithCaterpillars(List<Animal> allAnimals) {
        allAnimals.stream()
                .filter(animal -> animal.getType() == AnimalType.CATERPILLAR)
                .filter(animal -> animal.getCell().getPlants() == 0)
                .forEach(Animal::die);
    }

    private void runAnimalLifeCycle(List<Animal> allAnimals) {
        allAnimals.stream()
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

    private void setBirthdaysForNewAnimals(List<Animal> allAnimals) {
        allAnimals.stream()
                .filter(animal -> animal.getBirthDay() == 0)
                .forEach(animal -> animal.setBirthDay(Initializer.dayCounter));
    }
}
