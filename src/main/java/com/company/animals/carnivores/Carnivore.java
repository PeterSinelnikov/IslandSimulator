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
                .filter(cell -> cell.countType(getType()) < getMaxAmountPerCell())
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
            this.getCell().getAnimals().stream()
                    .filter(animal -> animal instanceof Herbivore)
                    .filter(victimAnimal -> AttackingPropParser.getInstance()
                            .getAttackingProperty(this.getType(), victimAnimal.getType()) > 0)
                    .max(Comparator.comparingDouble(Animal::getWeight))
                    .ifPresentOrElse(
                            this::tryToKill,
                            () -> setWeight(getWeight() - getMaxAmountPerCell())
                    );
        }
    }

    private Optional<Animal> lookForDeadHerbivore() {
        return this.getCell().getAnimals().stream()
                .filter(Animal::isDead)
                .filter(animal -> animal instanceof Herbivore)
                .filter(animal -> animal.getWeight() > 0)
                .findAny();
    }

    private void tryToKill(Animal victimAnimal) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double attackingProperty = AttackingPropParser.getInstance()
                .getAttackingProperty(this.getType(), victimAnimal.getType());
        victimAnimal.setOffenderName(this);
        if (random.nextDouble() < attackingProperty) {
            victimAnimal.die();
            feedOnDeadAnimal(victimAnimal);
        } else {
            setWeight(getWeight() - getMaxDailyWeightLoss());
        }
    }

    private void feedOnDeadAnimal(Animal victimAnimal) {
        double consumedFood;
        if (victimAnimal.getWeight() < getRequiredAmountOfFood()) {
            consumedFood = victimAnimal.getWeight();
            double weightLoss = (getRequiredAmountOfFood() - consumedFood) / getRequiredAmountOfFood() * getMaxDailyWeightLoss();
            setWeight(getWeight() - weightLoss);
        } else {
            setWeight(AnimalFieldsParser.getInstance().getWeight(getType()));
            consumedFood = getRequiredAmountOfFood();
        }
        victimAnimal.setWeight(victimAnimal.getWeight() - consumedFood);
    }

    private boolean hasChancesToKill(Cell cell) {
        return cell.getAnimals().stream()
                .anyMatch(animal -> AttackingPropParser.getInstance()
                        .getAttackingProperty(this.getType(), animal.getType()) > 0);
    }
}
