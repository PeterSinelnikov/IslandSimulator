package com.company.animals.carnivores;

import com.company.animals.Animal;
import com.company.animals.herbivores.Herbivore;
import com.company.config.properties.AnimalProperties;
import com.company.config.properties.AttackingProperties;
import com.company.island.Cell;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivore extends Animal {

    public Carnivore(Cell cell) {
        super(cell);
    }

    @Override
    protected Cell chooseWhereToGo(List<Cell> availableCellsToGo) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return availableCellsToGo.stream()
                .filter(cell -> cell.countType(getType()) < AnimalProperties.getMaxAmountPerCell(this.getType()))
                .filter(this::hasChancesToKill)
                .findAny()
                .orElseGet(() -> availableCellsToGo
                        .get(random.nextInt(0, availableCellsToGo.size())));
    }

    @Override
    public void eat() {
        Optional<Animal> deadAnimal = lookForDeadHerbivore();
        if (deadAnimal.isPresent()) {
            Animal victim = deadAnimal.get();
            feedOnDeadAnimal(victim);
        } else {
            this.getCell().getAnimals().stream()
                    .filter(animal -> animal instanceof Herbivore)
                    .filter(victimAnimal -> AttackingProperties
                            .getAttackingProperty(this.getType(), victimAnimal.getType()) > 0)
                    .max(Comparator.comparingDouble(Animal::getWeight))
                    .ifPresentOrElse(
                            this::tryToKill,
                            () -> setWeight(getWeight() - AnimalProperties.getMaxDailyWeightLoss(this.getType()))
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
        double attackingProperty = AttackingProperties
                .getAttackingProperty(this.getType(), victimAnimal.getType());
        victimAnimal.setOffenderName(this);
        if (random.nextDouble() < attackingProperty) {
            victimAnimal.die();
            feedOnDeadAnimal(victimAnimal);
        } else {
            setWeight(getWeight() - AnimalProperties.getMaxDailyWeightLoss(this.getType()));
        }
    }

    private void feedOnDeadAnimal(Animal victimAnimal) {
        double consumedFood;
        if (victimAnimal.getWeight() < AnimalProperties.getRequiredAmountOfFood(this.getType())) {
            consumedFood = victimAnimal.getWeight();
            double weightLoss = (AnimalProperties.getRequiredAmountOfFood(this.getType()) - consumedFood)
                    / AnimalProperties.getRequiredAmountOfFood(this.getType()) * AnimalProperties.getMaxDailyWeightLoss(this.getType());
            setWeight(getWeight() - weightLoss);
        } else {
            setWeight(AnimalProperties.getWeight(getType()));
            consumedFood = AnimalProperties.getRequiredAmountOfFood(this.getType());
        }
        victimAnimal.setWeight(victimAnimal.getWeight() - consumedFood);
    }

    private boolean hasChancesToKill(Cell cell) {
        return cell.getAnimals().stream()
                .anyMatch(animal -> AttackingProperties
                        .getAttackingProperty(this.getType(), animal.getType()) > 0);
    }
}
