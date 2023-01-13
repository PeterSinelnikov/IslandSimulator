package com.company.animals.herbivores.omnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Boar extends Omnivore {
    public Boar(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC17";
    }
}
