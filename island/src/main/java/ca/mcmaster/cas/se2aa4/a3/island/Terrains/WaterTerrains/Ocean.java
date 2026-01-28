package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Ocean extends BodiesWater {
    private Tile oceanTile;

    public Ocean(Tile tile){
        this.oceanTile=tile;
        this.humidity_level=15.0;
    }


    public Double getHumidityLevel() {
        return this.humidity_level;
    }

    public List<TileVertex> getMidPoints(){
        List<TileVertex> mid=new ArrayList<>();
        mid.add(oceanTile.getCentroid());
        return mid;
    }


}
