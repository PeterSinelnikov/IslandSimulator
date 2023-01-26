package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Eagle extends Carnivore{

    public Eagle(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.EAGLE;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }
}
