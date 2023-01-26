package com.company.animals.herbivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Rabbit extends Herbivore {
    public Rabbit(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.RABBIT;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07";
    }
}
