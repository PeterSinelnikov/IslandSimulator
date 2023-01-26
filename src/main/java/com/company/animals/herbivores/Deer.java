package com.company.animals.herbivores;


import com.company.animals.AnimalType;
import com.company.island.Cell;


public class Deer extends Herbivore {

    public Deer(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.DEER;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD8C";
    }
}
