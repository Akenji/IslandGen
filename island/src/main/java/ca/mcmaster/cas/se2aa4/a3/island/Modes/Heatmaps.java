package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;

import java.awt.*;
import java.io.IOException;

public class Heatmaps extends Regular{

    Double scale;
    Double red_scale;

    Double highest_temp;
    Double lowest_temp;


    public Heatmaps(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxRivers, SoilTypes soil, int numAquifers, int numCities) throws IOException {
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, maxLakes, maxRivers, soil, numAquifers, numCities);
        super.generate();
        this.highest_temp= temperature_gen.getHighestTemp();
        this.lowest_temp= temperature_gen.getLowestTemp();;

        setProperties();
    }
    public void setProperties(){
        for (Tile t: tiles){
            if (t.isTileLand()){
                Color new_color;
                Double col=(t.getAverageTemperature());

                Double val=(255) * (col - lowest_temp) / (highest_temp - lowest_temp);

                if (col>=273.15){
                    val=(255) * (col-Math.max(273.15,lowest_temp)) / (highest_temp - Math.max(273.15,lowest_temp));
                    new_color=new Color(255, 0,0,(int)(Math.abs(val)));
                }else{
                    val=(255) * (col - Math.min(180,lowest_temp)) / (highest_temp - Math.min(180,lowest_temp));
                    new_color=new Color(0, 0,255,(int)(255-Math.abs(val)));

                }


//                if (col<=0){
//                    if (col<-254){
//                        col=-254.0;
//                    }
//                    new_color=new Color(255-(int)Math.abs(col)-50, 255-(int)Math.abs(col),255);
//                }else{
//                    if (col>254){
//                        col=254.0;
//                    }
//                    new_color=new Color(255, 255-(int)Math.abs(col),255-(int)Math.abs(col)-20,(int)Math.abs(col));
//                }

                t.setPolygonColor(new_color);
            }else{
                t.setPolygonColor(new Color(0,0,0));
            }
        }
    }


}
