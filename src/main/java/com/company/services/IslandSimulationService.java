package com.company.services;

import com.company.config.parsers.IslandPropParser;
import com.company.island.Island;

import java.util.concurrent.*;

public class IslandSimulationService {

    private static final int STEP_DURATION = IslandPropParser.getInstance().getDayDuration();
    private static final ScheduledExecutorService PLANTS_SERVICE = Executors.newScheduledThreadPool(2);
    private static final ScheduledExecutorService ANIMALS_SERVICE = Executors.newScheduledThreadPool(2);

    public static void startSimulation() {
        startAnimalLifeCycle();
        growPlants();
    }

    private static void growPlants() {
        PLANTS_SERVICE.scheduleAtFixedRate(new PlantsGrowthService()
                , STEP_DURATION, STEP_DURATION, TimeUnit.MILLISECONDS);
    }

    private static void startAnimalLifeCycle() {
        ANIMALS_SERVICE.scheduleAtFixedRate(new AnimalSimulationService()
                , 0, STEP_DURATION,TimeUnit.MILLISECONDS);
    }

    protected static void stopSimulation() {
        PLANTS_SERVICE.shutdown();
        ANIMALS_SERVICE.shutdown();
        System.out.println("The last animal on the Island is " + Island.getAllAnimals().get(0));
    }

}
