package ca.mcmaster.cas.se2aa4.a3.island.Temperature;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Temperature {

    List<Tile> all_tiles;
    Double base_temperature;

    Double lowest_temperature;

    Double highest_temperature;

    public Temperature(){
        all_tiles=new ArrayList<>();
        this.lowest_temperature=Double.MAX_VALUE;
        this.highest_temperature=-1*Double.MAX_VALUE;
    }

    public void setTemperature(List<Tile> tiles, double baseTemperature, Double min_elevation){
        this.all_tiles=tiles;
        this.base_temperature= baseTemperature;


        for (Tile t: all_tiles){
            Double average=0.0;
            for (TileVertex v:t.getTileVertices()){
                v.setTemperature(base_temperature-0.03*(v.getElevation()));
                average+=v.getTemperature();
            }
            average/=t.getTileVertices().size();

            if (average<=0){
                average=0.1;
            }

            if (average<lowest_temperature){
                lowest_temperature =average;
            }
            if (average>highest_temperature){
                highest_temperature=average;
            }

            t.setAverageTemperature(average);
        }
    }
    public Double getLowestTemp(){
        return this.lowest_temperature;
    }
    public Double getHighestTemp(){
        return this.highest_temperature;
    }
    public double getTemperatureRange(){
        return this.highest_temperature - this.lowest_temperature;
    }



}

