package com.company.animals;

public enum AnimalType {
    WOLF(1),
    BOA(2),
    FOX(3),
    BEAR(4),
    EAGLE(5),
    HORSE(6),
    DEER(7),
    RABBIT(8),
    MOUSE(9),
    GOAT(10),
    SHEEP(11),
    BOAR(12),
    BUFFALO(13),
    DUCK(14),
    CATERPILLAR(15);

    public final int index;

    AnimalType(int index) {
        this.index = index;
    }
}
