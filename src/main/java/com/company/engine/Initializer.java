package com.company.engine;

import com.company.animals.Animal;
import com.company.animals.animalFactory.Factory;
import com.company.animals.AnimalType;
import com.company.config.properties.AnimalProperties;
import com.company.island.Island;


public class Initializer {
    public static int dayCounter;

    public static void startSimulation() {
        dayCounter++;
        Island.initializeIsland();
        initializeAnimals();
        IslandSimulationService.startSimulation();
    }

    private static void initializeAnimals() {
        for (AnimalType type : AnimalType.values()) {
            int quantityOfAnimals = AnimalProperties.getInitialQuantity(type);
            for (int i = 0; i < quantityOfAnimals; i++) {
                Animal animal = Factory.getInstance().createAnimal(type, Island.getRandomCell());
                animal.setBirthDay(dayCounter);
            }
        }
    }
}