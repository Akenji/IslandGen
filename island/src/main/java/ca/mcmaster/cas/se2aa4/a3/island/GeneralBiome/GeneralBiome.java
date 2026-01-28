package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public abstract class GeneralBiome {
    protected final double protectedTemperature;
    public WhittakerDiagram whittakerDiagram;
    public GeneralBiome(double protectedTemperature){
        this.protectedTemperature = protectedTemperature;
    }

    public double getBaseTemperature()
    {
        return protectedTemperature;
    }

    public abstract void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature);

    public abstract TileTypes getTileBiome(double humidity, double temperature);
}
