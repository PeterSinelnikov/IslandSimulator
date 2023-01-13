package com.company.island;

import com.company.animals.Animal;
import com.company.config.parsers.IslandPropParser;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Island {
    @Getter
    private static int xAmountOfCells;
    @Getter
    private static int yAmountOfCells;
    @Getter
    private static Cell[][] cells;
    private static List<List<Animal>> allAnimalsList;

    private Island() {}

    public static void initializeIsland() {
        initializeIslandSize();
        initializeCells();
        initializeAllAnimalsList();
        initializePlants();
    }

    private static void initializeIslandSize() {
        xAmountOfCells = IslandPropParser.getInstance().getXAmountOfCells();
        yAmountOfCells = IslandPropParser.getInstance().getYAmountOfCells();
    }
    private static void initializeCells() {
        cells = new Cell[xAmountOfCells][yAmountOfCells];
        for (int i = 0; i < xAmountOfCells; i++) {
            for (int j = 0; j < yAmountOfCells; j++) {
                Cell cell = new Cell(i,j, new ArrayList<>());
                cells[i][j] = cell;
            }
        }
    }

    private static void initializeAllAnimalsList() {
        allAnimalsList = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .map(Cell::getAnimals)
                .collect(Collectors.toList());
    }

    private static void initializePlants() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .forEach(cell-> cell.setPlants(random.nextInt(0,Cell.MAX_AMOUNT_OF_PLANTS)));
    }

    public static List<Animal> getAllAnimals() {
        return allAnimalsList.stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public static Cell getRandomCell() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return cells[random.nextInt(0,Island.xAmountOfCells)]
                [random.nextInt(0,Island.yAmountOfCells)];
    }
}
