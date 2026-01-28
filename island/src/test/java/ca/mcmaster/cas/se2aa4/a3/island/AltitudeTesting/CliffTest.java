package ca.mcmaster.cas.se2aa4.a3.island.AltitudeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.Altitude;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeFunction;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.Cliff;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CliffTest extends SetUpTest {

    //Tests only the cliff function
    @Test
    public void checkElevationFunction(){
        AltitudeFunction cliff_function=new Cliff().getFunction();

        double highest_possible1=cliff_function.valueAt(100.0,100.0);
        double highest_possible2=cliff_function.valueAt(0.0,100.0);

        double test=cliff_function.valueAt(50.0,50.0);



        if (highest_possible1>test|highest_possible2>test){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    //Tests island vertices that cliff was applied to.
    @Test
    public void CliffVerticesTest(){

        Altitude new_altitude=new Altitude();

        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.CLIFF, all_tiles);

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

        if ((width/2-1<=highest_vertex.getX() & height/2-1<= highest_vertex.getY()) | (width>=highest_vertex.getX() & height/2-1<= highest_vertex.getY())|(width>=highest_vertex.getX() & height>=highest_vertex.getY()) | (width/2-1<=highest_vertex.getX() & height>= highest_vertex.getY())){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

}
