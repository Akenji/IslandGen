package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.Lake;

import java.util.ArrayList;
import java.util.List;

public class Altitude {

    List<Tile> tiles;

    Double min_elevation;

    AltitudeFunction function;

    public Altitude(){
        tiles=new ArrayList<>();
        min_elevation=Double.MAX_VALUE;
    }

    public Double getMinElevation(){
        return this.min_elevation;
    }

    public void setAll(AltitudeType altitude, List<Tile> landtiles, List<Tile> oceanTiles, List<Lake> lakes){
        SetElevation(altitude, landtiles);
        LevelLand(landtiles);

        setLakes(lakes);
    }


    public void setLakes(List<Lake> lake_objects){
        for (Lake lake:lake_objects){
            SetElevation(AltitudeType.WATER, lake.getLakeTiles());
        }
    }

    public void LevelLand( List<Tile> tiles){
        List<TileVertex> vertices=new ArrayList<>();
        for (Tile tile:tiles){
            for (TileVertex vertex: tile.getTileVertices()){
                if (!vertices.contains(vertex)) {
                    vertex.setElevation(vertex.getElevation()-min_elevation);
                    vertices.add(vertex);
                }
            }
        }
    }

    public void SetElevation(AltitudeType altitude, List<Tile> tiles){
        this.min_elevation=Double.MAX_VALUE;
        this.tiles=tiles;
        this.function=altitude.getAltitude(tiles);
        for(Tile tile: tiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));
                if (vertex.getElevation()<min_elevation){
                    min_elevation=vertex.getElevation();
                }
            }
        }
    }

}
