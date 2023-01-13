package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Boa extends Carnivore {
    public Boa(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}
