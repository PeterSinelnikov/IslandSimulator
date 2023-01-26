package com.company.animals.herbivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Buffalo extends Herbivore{
    public Buffalo(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BUFFALO;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC03";
    }
}
