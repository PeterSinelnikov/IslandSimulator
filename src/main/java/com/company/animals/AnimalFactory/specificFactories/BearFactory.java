package com.company.animals.AnimalFactory.specificFactories;

import com.company.animals.Animal;
import com.company.animals.AnimalFactory.AnimalFactory;
import com.company.animals.AnimalType;
import com.company.animals.carnivores.Bear;
import com.company.island.Cell;

public class BearFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(Cell cell, AnimalType type) {
        return new Bear(cell, type);
    }
}
