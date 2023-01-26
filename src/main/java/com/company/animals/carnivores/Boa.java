package com.company.animals.carnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Boa extends Carnivore {

    public Boa(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BOA;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}
