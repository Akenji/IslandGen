package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.ArrayList;
import java.util.List;

public class Lake extends BodiesWater {

    private List<Tile> lakeTiles;

    public Lake(List<Tile> lakeTiles){
        this.humidity_level=12.0;
        this.lakeTiles=lakeTiles;
    }



    public List<Tile> getLakeTiles() {
        return lakeTiles;
    }

    public Double getHumidityLevel() {
        return this.humidity_level;
    }

    public List<TileVertex> getMidPoints() {
        List<TileVertex> all_centroid=new ArrayList<>();

        for (Tile t:lakeTiles){
            all_centroid.add(t.getCentroid());

        }
        return all_centroid;
    }
}
