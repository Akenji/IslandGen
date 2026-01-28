package ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;

public enum CityType {
    HAMLET(200, 2000),
    MEGACITY(500000, 2000000),
    CAPITAL(100000,1000000);

    private final int minPopulation;
    private final int maxPopulation;

    private CityType(int minPopulation, int maxPopulation){
        this.minPopulation = minPopulation;
        this.maxPopulation = maxPopulation;
    }

    public int getPopulation(){
        int population = IslandCommandLineReader.randomGenerator.getNextInteger(minPopulation, maxPopulation);
        return (int) Math.round(mapPopulation(population));
    }

    private double mapPopulation(int population){
        double logPopulation = Math.log(population);
        double logFunction = (logPopulation - Math.log(200)) / (Math.log(2000000) - Math.log(200));
        double thicknessRange = 20.0 - 10.0; // maximum 20, minimum 10
        return 5 + thicknessRange * logFunction; // minimum thickness value of 10.0
    }

    @Override
    public String toString() {
        return switch (this) {
            case HAMLET -> "Hamlet";
            case MEGACITY -> "City";
            case CAPITAL -> "Capital";
        };
    }
}
