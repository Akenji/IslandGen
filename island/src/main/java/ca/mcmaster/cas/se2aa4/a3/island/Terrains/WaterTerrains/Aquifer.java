package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Aquifer extends BodiesWater{
    private Tile aquiferTile;
    public Aquifer(Tile aquiferTile){
        this.aquiferTile = aquiferTile;
        this.humidity_level = 5.0;
        // add humidity level
    }
    public Double getHumidityLevel() {
        return this.humidity_level;
    }
    public List<TileVertex> getMidPoints(){
        List<TileVertex> mid=new ArrayList<>();
        mid.add(aquiferTile.getCentroid());
        return mid;
    }

    public Tile getAquiferTile(){return this.aquiferTile;}

}
