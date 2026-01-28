package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.util.HashMap;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.Shape;

public class WhittakerDiagram {
    private final double scaleHumidity;
    private final double scaleTemperature;
    private final double minHumidity;
    private final double minTemperature;
    private HashMap<Shape, TileTypes> whittakerDiagram;
    
    public WhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        this.scaleHumidity = 100 / humidityRange; 
        this.scaleTemperature = 100 / temperatureRange;
        this.minHumidity = minHumidity;
        this.minTemperature = minTemperature;
        this.whittakerDiagram = new HashMap<Shape, TileTypes>();
    }

    public void addToWhittakerDiagram(Shape shape, TileTypes biome){
        whittakerDiagram.put(shape, biome);
    }

    public TileTypes getBiome(double humidity, double temperature){
        //Get the biome(tileTypes) based on the whittaker diagram
        for(Shape area: whittakerDiagram.keySet()){
            if (area.contains((humidity - minHumidity)*scaleHumidity , (temperature - minTemperature)*scaleTemperature))
            {
                //Returns the TileTypes for that area
                return whittakerDiagram.get(area);
            }
        }

        return TileTypes.GRASSLAND;
    }

    public HashMap<Shape, TileTypes> getWhittakerDiagram(){
        return whittakerDiagram;
    }
    
}
