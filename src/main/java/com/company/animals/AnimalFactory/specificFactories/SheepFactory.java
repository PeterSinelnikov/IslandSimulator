package com.company.animals.AnimalFactory.specificFactories;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory.AnimalFactory;
import com.company.animals.AnimalType;
import com.company.animals.herbivores.Sheep;
import com.company.island.Cell;

public class SheepFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(Cell cell, AnimalType type) {
        return new Sheep(cell, type);
    }
}
