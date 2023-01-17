package com.company.animals.herbivores.omnivores;

import com.company.animals.Animal;
import com.company.animals.AnimalType;
import com.company.animals.herbivores.Herbivore;
import com.company.config.parsers.AttackingPropParser;
import com.company.island.Cell;

import java.util.concurrent.ThreadLocalRandom;


public abstract class Omnivore extends Herbivore {

    public Omnivore(Cell cell, AnimalType type) {
        super(cell, type);
    }

    @Override
    public void eat() {
        super.eat();
        getCell().getAnimals().stream()
                .filter(victimAnimal -> AttackingPropParser.getInstance()
                        .getAttackingProperty(this.getType(), victimAnimal.getType()) > 0)
                .findAny()
                .ifPresent(this::tryToKill);
    }

    private void tryToKill(Animal victimAnimal) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double attackingProperty = AttackingPropParser.getInstance()
                .getAttackingProperty(this.getType(), victimAnimal.getType());
        victimAnimal.setOffenderName(this);
        if (random.nextDouble() < attackingProperty) {
            victimAnimal.die();
            this.setWeight(getWeight() + victimAnimal.getWeight());
            victimAnimal.setWeight(0);
        }
    }
}
