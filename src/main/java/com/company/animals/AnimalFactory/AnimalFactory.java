package com.company.animals.AnimalFactory;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.island.Cell;

public interface AnimalFactory {
    Animal createAnimal(Cell cell, AnimalType type);
}
