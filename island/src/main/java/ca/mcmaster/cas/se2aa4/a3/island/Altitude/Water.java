package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Water implements WaterAltitude {

    Double elevation;

    List<Tile> tiles;

    public AltitudeFunction function= (x,y)->{return elevation;};

    public AltitudeFunction getFunction(List<Tile> tiles){
        this.tiles=tiles;
        findElevation();
        return this.function;
    }

    public void findElevation(){
        Double minimum=Double.MAX_VALUE;

        for (Tile t:tiles){
            for (TileVertex v:t.getTileVertices()){
                if (v.getElevation()<minimum & v.getElevation()>1.0 & t.isTileWater()){
                    minimum=v.getElevation();
                }
            }
        }


        elevation=(minimum)*0.6;
    }




}
