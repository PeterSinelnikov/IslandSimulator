package com.company.engine;

import com.company.animals.Animal;
import com.company.animals.carnivores.Carnivore;
import com.company.animals.herbivores.Herbivore;
import com.company.island.Cell;
import com.company.island.Island;

import java.util.Arrays;
import java.util.List;


public class StatsPrinter {

    public static void printDayStats() {
        System.out.println("Day " + Initializer.dayCounter);
        List<Animal> allAnimals = Island.getAllAnimals();
        printKilledAnimals(allAnimals);
        printDiedFromStarvation(allAnimals);
        printFailedAttemptsToKill(allAnimals);
        printNewBabies(allAnimals);
        printTotalAmountOfCarnivores(allAnimals);
        printTotalAmountOfHerbivores(allAnimals);
        printDesertedCells();
        printIslandDensity();
        System.out.println("------------------------------");
    }

    private static void printKilledAnimals(List<Animal> allAnimals) {
        long killedAnimals = allAnimals.stream()
                .filter(Animal::isDead)
                .filter(animal -> animal.getCorpseDayCounter() == 0)
                .filter(animal -> animal.getOffenderName() != null)
                .count();
        System.out.printf("animals killed by carnivores and omnivores: %s\n", killedAnimals);
    }

    private static void printFailedAttemptsToKill(List<Animal> allAnimals) {
        long failedAttempts = allAnimals.stream()
                .filter(animal -> animal.getOffenderName() != null)
                .peek(animal -> animal.setOffenderName(null))
                .filter(animal -> !animal.isDead())
                .count();
        System.out.printf("failed attempts to kill: %s\n", failedAttempts);
    }

    private static void printDiedFromStarvation(List<Animal> allAnimals) {
        long diedFromStarvation = allAnimals.stream()
                .filter(Animal::isDead)
                .filter(animal -> animal.getCorpseDayCounter() == 0)
                .filter(animal -> animal.getOffenderName() == null)
                .count();
        System.out.printf("died from starvation: %s\n", diedFromStarvation);
    }

    private static void printNewBabies(List<Animal> allAnimals) {
        long babyQuantity = allAnimals.stream()
                .filter(animal -> animal.getBirthDay() == Initializer.dayCounter)
                .count();
        System.out.printf("new babies born today: %s\n", babyQuantity);
    }

    private static void printTotalAmountOfCarnivores(List<Animal> allAnimals) {
        System.out.printf("total amount of carnivores: %s\n",
                allAnimals.stream()
                        .filter(animal -> animal instanceof Carnivore)
                        .filter(animal -> !animal.isDead())
                        .count());
    }

    private static void printTotalAmountOfHerbivores(List<Animal> allAnimals) {
        System.out.printf("total amount of herbivores: %s\n",
                allAnimals.stream()
                        .filter(animal -> animal instanceof Herbivore)
                        .filter(animal -> !animal.isDead())
                        .count());
    }

    private static void printDesertedCells() {
        System.out.printf("cells with no more plants: %s\n",
                Arrays.stream(Island.getCells())
                        .flatMap(Arrays::stream)
                        .filter(cell -> cell.getPlants() == 0)
                        .count());
    }

    private static void printIslandDensity() {
        System.out.println("legend: ?????? - 0 animals, " +
                "\uD83D\uDFE9 - 1-3 animals, " +
                "\uD83D\uDFE8 - 4-6 animals, " +
                "\uD83D\uDFE7 - 6-9 animals, " +
                "\uD83D\uDFE5 - 10+ animals");
        Cell[][] islandCells = Island.getCells();
        for (int i = 0; i < Island.getYAmountOfCells(); i++) {
            for (int j = 0; j < Island.getXAmountOfCells(); j++) {
                int amountOfAnimalsInCell = islandCells[j][i].getAnimals().size();
                chooseColorToPrint(amountOfAnimalsInCell);
            }
            System.out.println();
        }

    }

    private static void chooseColorToPrint(int amountOfAnimalsInCell) {
        if (amountOfAnimalsInCell == 0) {
            System.out.print("??????");
        }
        if (amountOfAnimalsInCell > 0 && amountOfAnimalsInCell <= 3) {
            System.out.print("\uD83D\uDFE9");
        }
        if (amountOfAnimalsInCell > 3 && amountOfAnimalsInCell <= 6) {
            System.out.print("\uD83D\uDFE8");
        }
        if (amountOfAnimalsInCell > 6 && amountOfAnimalsInCell <= 9) {
            System.out.print("\uD83D\uDFE7");
        }
        if (amountOfAnimalsInCell > 9) {
            System.out.print("\uD83D\uDFE5");
        }
    }
}