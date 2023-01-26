package com.company.animals.herbivores.omnivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Mouse extends Omnivore {
    public Mouse(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.MOUSE;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC01";
    }
}
