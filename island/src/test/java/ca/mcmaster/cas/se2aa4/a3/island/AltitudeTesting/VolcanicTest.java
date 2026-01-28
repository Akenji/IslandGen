package ca.mcmaster.cas.se2aa4.a3.island.AltitudeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VolcanicTest extends SetUpTest {

    //Checks volcanic elevation function only. Arbitrary numbers.
    @Test
    public void checkElevationFunction(){
        AltitudeFunction volcanic_function=new Volcanic().getFunction();

        double highest_possible1=volcanic_function.valueAt(width/2,height/2);

        double test=volcanic_function.valueAt(0.0,0.0);


        if (highest_possible1>test){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    //Checks volcanic elevation applied to vertices on physical island.
    @Test
    public void VolcanicVerticesTest() {

        Altitude new_altitude=new Altitude();
        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.VOLCANIC, all_tiles);

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



        if (width/2-width*0.25<= highest_vertex.getX() & height/2-height*0.25<=highest_vertex.getY()){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }
}
