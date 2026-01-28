package ca.mcmaster.cas.se2aa4.a3.island.SoilProfile;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.Land;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.BodiesWater;

import java.awt.*;
import java.util.List;

public class Humidity {

    private double maxHumidity = -Double.MAX_VALUE;
    private double minHumidity = Double.MAX_VALUE;
    private double humidityRange = 1;

    public Double calculateDistance(TileVertex v1, TileVertex v2){
        Double x1=v1.getX();
        Double x2=v2.getX();

        Double y1=v1.getY();
        Double y2=v2.getY();

        Double distance=Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));

        return distance;
    }

    public void SetHumidity(List<Land> landTiles, List<BodiesWater> allWater){
        for (Land land:landTiles){
            Double averageHumidity=0.0;
            Tile tile=land.getTile();
            for (BodiesWater water:allWater){
                for (TileVertex v: water.getMidPoints()){
                    Double distance=calculateDistance(tile.getCentroid(),v);
                    Double humidity=(water.getHumidityLevel()*land.getCoefficient())/(distance+1);
                    averageHumidity+=humidity;
                }
            }
            land.setHumidity(averageHumidity);

            if (averageHumidity > maxHumidity){
                maxHumidity = averageHumidity;
            }
            if (averageHumidity < minHumidity){
                minHumidity = averageHumidity;
            }
        }
        humidityRange = maxHumidity - minHumidity;
    }

    public void setHumidityColors(List<Land> landTiles){
        Tile editTile;
        for (Land land: landTiles){
            editTile = land.getTile();
            if (editTile.isTileSoil()){
                Color c_new=newColor(editTile.getColor(), land.getHumidity()/2);
                editTile.setPolygonColor(c_new);
            }
        }
    }

    private Color newColor(Color c, Double averageHumidity){
        int new_red=(int)Math.round(c.getRed()-averageHumidity);

        if (new_red<0){
            new_red=0;
        }

        int new_blue=(int)Math.round(c.getBlue()-averageHumidity);
        if (new_blue<0){
            new_blue=0;
        }

        int new_green=(int)Math.round(c.getGreen()-averageHumidity);
        if (new_green<50){
            new_green=50;
        }
        return new Color(new_red,new_green,new_blue);

    }

    public double getHumidityRange(){
        return humidityRange;
    }

    public double getMinHumidity(){
        return minHumidity;
    }
}
