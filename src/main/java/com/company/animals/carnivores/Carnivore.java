package com.company.animals.carnivores;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.animals.herbivores.Herbivore;
import com.company.config.parsers.AnimalFieldsParser;
import com.company.config.parsers.AttackingPropParser;
import com.company.island.Cell;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {

    public Carnivore(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    protected Cell chooseWhereToGo(List<Cell> availableCellsToGo) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return availableCellsToGo.stream()
                .filter(cell -> cell.countType(type) < maxAmountPerCell)
                .filter(this::hasChancesToKill)
                .findAny()
                .orElseGet(() -> availableCellsToGo
                        .get(random.nextInt(0, availableCellsToGo.size())));
    }

    @Override
    public void eat() {
        if (lookForDeadHerbivore().isPresent()) {
            Animal victim = lookForDeadHerbivore().get();
            feedOnDeadAnimal(victim);
        } else {
            cell.getAnimals().stream()
                    .filter(animal -> animal instanceof Herbivore)
                    .filter(victimAnimal -> AttackingPropParser.getInstance()
                            .getAttackingProperty(this.type, victimAnimal.getType()) > 0)
                    .max(Comparator.comparingDouble(Animal::getWeight))
                    .ifPresentOrElse(
                            this::tryToKill,
                            () -> weight = weight - maxDailyWeightLoss
                    );
        }
    }

    private Optional<Animal> lookForDeadHerbivore() {
        return this.cell.getAnimals().stream()
                .filter(Animal::isDead)
                .filter(animal -> animal instanceof Herbivore)
                .filter(animal -> animal.getWeight() > 0)
                .findAny();
    }

    private void tryToKill(Animal victimAnimal) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double attackingProperty = AttackingPropParser.getInstance()
                .getAttackingProperty(this.type, victimAnimal.getType());
        victimAnimal.setOffenderName(this);
        if (random.nextDouble() < attackingProperty) {
            victimAnimal.die();
            feedOnDeadAnimal(victimAnimal);
        } else {
            weight = weight - maxDailyWeightLoss;
        }
    }

    private void feedOnDeadAnimal(Animal victimAnimal) {
        double consumedFood;
        if (victimAnimal.getWeight() < requiredAmountOfFood) {
            consumedFood = victimAnimal.getWeight();
            double weightLoss = (requiredAmountOfFood - consumedFood) / requiredAmountOfFood * maxDailyWeightLoss;
            weight = weight - weightLoss;
        } else {
            weight = AnimalFieldsParser.getInstance().getWeight(type);
            consumedFood = requiredAmountOfFood;
        }
        victimAnimal.setWeight(victimAnimal.getWeight() - consumedFood);
    }

    private boolean hasChancesToKill(Cell cell) {
        return cell.getAnimals().stream()
                .anyMatch(animal -> AttackingPropParser.getInstance()
                        .getAttackingProperty(this.type, animal.getType()) > 0);
    }
}
