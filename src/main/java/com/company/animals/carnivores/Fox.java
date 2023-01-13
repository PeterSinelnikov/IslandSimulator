package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Fox extends Carnivore{

    public Fox(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
