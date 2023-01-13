package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Wolf extends Carnivore {

    public Wolf(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }
}
