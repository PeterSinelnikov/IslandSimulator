package com.company.animals.herbivores;

import com.company.animals.AnimalType;
import com.company.island.Cell;

public class Sheep extends Herbivore{
    public Sheep(Cell cell) {
        super(cell);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.SHEEP;
    }
    
    @Override
    public String toString() {
        return "\uD83D\uDC11";
    }
}
