package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Wolf extends Carnivore {

    public Wolf(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.WOLF;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
