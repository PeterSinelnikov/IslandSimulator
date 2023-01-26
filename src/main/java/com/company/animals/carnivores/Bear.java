package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Bear extends Carnivore {

    public Bear(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BEAR;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3B";
    }
}
