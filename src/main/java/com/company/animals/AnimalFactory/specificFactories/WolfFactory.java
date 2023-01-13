package com.company.animals.AnimalFactory.specificFactories;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory.AnimalFactory;
import com.company.animals.AnimalType;
import com.company.animals.carnivores.Wolf;
import com.company.island.Cell;

public class WolfFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(Cell cell, AnimalType type) {
        return new Wolf(cell, type);
    }
}
