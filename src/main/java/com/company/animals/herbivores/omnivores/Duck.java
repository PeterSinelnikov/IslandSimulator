package com.company.animals.herbivores.omnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Duck extends Omnivore {
    public Duck(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public String toString() {
        return "\uD83E\uDD86";
    }
}
