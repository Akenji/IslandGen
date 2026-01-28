package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.*;

public class TemperateForestGeneralBiome extends GeneralBiome{
    public TemperateForestGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        whittakerDiagram = new WhittakerDiagram(humidityRange, minHumidity, temperatureRange, minTemperature);

        //Shapes altogether cover 0 - 100 on both axis
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, -1,102,12), TileTypes.SNOW);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 10,22,41), TileTypes.DIRT);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 50,22,52), TileTypes.ROCK);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(20, 25,46,26), TileTypes.GRASSLAND);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(20, -1,46,26), TileTypes.TAIGA);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(20, 49,46,51), TileTypes.FOREST);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(65, -1,37,26), TileTypes.TAIGA);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(65, 25,37,76), TileTypes.FOREST);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return whittakerDiagram.getBiome(humidity, temperature);
    }
}
