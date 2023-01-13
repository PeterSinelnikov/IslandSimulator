package com.company.services;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory.Factory;
import com.company.animals.AnimalType;
import com.company.config.parsers.AnimalFieldsParser;
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
            int quantityOfAnimals = AnimalFieldsParser.getInstance().getInitialQuantity(type);
            for (int i = 0; i < quantityOfAnimals; i++) {
                Animal animal = Factory.getInstance().createAnimal(type, Island.getRandomCell());
                animal.setBirthDay(dayCounter);
            }
        }
    }
}
