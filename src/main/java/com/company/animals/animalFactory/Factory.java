package com.company.animals.animalFactory;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.animals.carnivores.*;
import com.company.animals.herbivores.*;
import com.company.animals.herbivores.omnivores.*;
import com.company.island.Cell;

public class Factory {
    private static Factory instance;

    private Factory(){}

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Animal createAnimal(AnimalType type, Cell cell) {
        return switch (type) {
            case WOLF -> new Wolf(cell);
            case BOA -> new Boa(cell);
            case FOX -> new Fox(cell);
            case BEAR -> new Bear(cell);
            case EAGLE -> new Eagle(cell);
            case HORSE -> new Horse(cell);
            case DEER -> new Deer(cell);
            case RABBIT -> new Rabbit(cell);
            case MOUSE -> new Mouse(cell);
            case GOAT -> new Goat(cell);
            case SHEEP -> new Sheep(cell);
            case BOAR -> new Boar(cell);
            case BUFFALO -> new Buffalo(cell);
            case DUCK -> new Duck(cell);
            case CATERPILLAR -> new Caterpillar(cell);
        };
    }
}
