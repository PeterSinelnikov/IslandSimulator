package com.company.engine;

import com.company.config.properties.IslandProperties;
import com.company.island.Island;

import java.util.concurrent.*;

public class IslandSimulationService {

    private static final int STEP_DURATION = IslandProperties.getDayDuration();
    private static final ScheduledExecutorService PLANTS_EXECUTOR = Executors.newScheduledThreadPool(1);
    private static final ScheduledExecutorService ANIMALS_EXECUTOR = Executors.newScheduledThreadPool(1);

    public static void startSimulation() {
        startAnimalLifeCycle();
        growPlants();
    }

    private static void startAnimalLifeCycle() {
        ANIMALS_EXECUTOR.scheduleAtFixedRate(new AnimalDaySimulation()
                , 0, STEP_DURATION,TimeUnit.MILLISECONDS);
    }

    private static void growPlants() {
        PLANTS_EXECUTOR.scheduleAtFixedRate(new PlantsGrowthCycle()
                , STEP_DURATION, STEP_DURATION, TimeUnit.MILLISECONDS);
    }

    protected static void stopSimulation() {
        PLANTS_EXECUTOR.shutdown();
        ANIMALS_EXECUTOR.shutdown();
        System.out.println("Simulation is over. The last animal on the Island is " + Island.getAllAnimals().get(0));
    }

}
