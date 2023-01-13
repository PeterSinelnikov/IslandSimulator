package com.company.animals.herbivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Caterpillar extends Herbivore{
    public Caterpillar(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC1B";
    }
}
