package ca.mcmaster.cas.se2aa4.a3.island.AltitudeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlatTest extends SetUpTest {

    //Checks only the flat function.
    @Test
    public void checkElevationFunction(){
        AltitudeFunction volcanic_function=new Flat().getFunction();

        double e1=volcanic_function.valueAt(width/2,height/2);

        double e2=volcanic_function.valueAt(0.0,0.0);


        if (Math.abs(e1-e2)<=100.0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }


    //Checks island vertices with flat elevation.
    @Test
    public void FlatVerticesTest() {

        Altitude new_altitude=new Altitude();

        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.FLAT, all_tiles);

        TileVertex highest_vertex=all_tiles.get(0).getTileVertex(0);
        TileVertex lowest_vertex=all_tiles.get(0).getTileVertex(0);

        for (Tile t:all_tiles){
            for (TileVertex v: t.getTileVertices()){

                if (v.getElevation()>highest_vertex.getElevation()){
                    highest_vertex=v;
                }
                if (v.getElevation()<lowest_vertex.getElevation()){
                    lowest_vertex=v;
                }
            }
        }


        if (Math.abs(highest_vertex.getElevation()-lowest_vertex.getElevation())<=100.0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

}
