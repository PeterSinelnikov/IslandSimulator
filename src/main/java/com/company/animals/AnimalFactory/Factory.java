package com.company.animals.AnimalFactory;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory.specificFactories.*;
import com.company.animals.AnimalType;
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
            case WOLF -> new WolfFactory().createAnimal(cell,type);
            case BOA -> new BoaFactory().createAnimal(cell, type);
            case FOX -> new FoxFactory().createAnimal(cell, type);
            case BEAR -> new BearFactory().createAnimal(cell, type);
            case EAGLE -> new EagleFactory().createAnimal(cell, type);
            case HORSE -> new HorseFactory().createAnimal(cell, type);
            case DEER -> new DeerFactory().createAnimal(cell, type);
            case RABBIT -> new RabbitFactory().createAnimal(cell, type);
            case MOUSE -> new MouseFactory().createAnimal(cell, type);
            case GOAT -> new GoatFactory().createAnimal(cell, type);
            case SHEEP -> new SheepFactory().createAnimal(cell, type);
            case BOAR -> new BoarFactory().createAnimal(cell, type);
            case BUFFALO -> new BuffaloFactory().createAnimal(cell, type);
            case DUCK -> new DuckFactory().createAnimal(cell, type);
            case CATERPILLAR -> new CaterpillarFactory().createAnimal(cell, type);
        };
    }
}
