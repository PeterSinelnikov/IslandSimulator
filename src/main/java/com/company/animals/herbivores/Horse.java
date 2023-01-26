package com.company.animals.herbivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Horse extends Herbivore{
    public Horse(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.HORSE;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0E";
    }
}
