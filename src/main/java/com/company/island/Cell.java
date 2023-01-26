package com.company.island;

import com.company.animals.Animal;
import com.company.animals.carnivores.Carnivore;
import com.company.animals.AnimalType;
import com.company.config.properties.IslandProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Cell {
    public static final int MAX_AMOUNT_OF_PLANTS = IslandProperties.getMaxQuantityOfPlantsPerCell();
    private final int x;
    private final int y;
    @Getter
    private final List<Animal> animals;
    @Getter
    @Setter
    private int plants;

    public Cell(int x, int y, List<Animal> animals) {
        this.x = x;
        this.y = y;
        this.animals = animals;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean containsCarnivore() {
        return animals.stream().anyMatch(animal -> animal instanceof Carnivore);
    }
    public int countType(AnimalType type) {
        return (int) animals.stream().filter(animal -> animal.getType() == type).count();
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y
                +'}';
    }


}
