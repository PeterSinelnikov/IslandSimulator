package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Eagle extends Carnivore{
    public Eagle(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
