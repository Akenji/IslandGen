package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.*;

public class DesertGeneralBiome extends GeneralBiome{
    public DesertGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        whittakerDiagram = new WhittakerDiagram(humidityRange, minHumidity, temperatureRange, minTemperature);

        //Shapes altogether cover 0 - 100 on both axis
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 30,71,71), TileTypes.SAND);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, -1,71,32), TileTypes.DIRT);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(70, 60,32,41), TileTypes.SAVANNA);
        whittakerDiagram.addToWhittakerDiagram(new Rectangle(70, -1,32,62), TileTypes.CLAY);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return whittakerDiagram.getBiome(humidity, temperature);
    }
}
