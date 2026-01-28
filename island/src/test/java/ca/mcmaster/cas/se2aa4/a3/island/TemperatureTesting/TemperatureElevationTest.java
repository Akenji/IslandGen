package ca.mcmaster.cas.se2aa4.a3.island.TemperatureTesting;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemperatureElevationTest extends SetUpTest {

    //Testing whether temperature decreases with elevation.
    @Test
    public void TestElevationTemperature(){
        TileVertex lowest_ele=tiles.get(0).getTileVertex(0);
        TileVertex highest_ele=tiles.get(0).getTileVertex(0);

        for (Tile t:tiles){
            for (TileVertex v:t.getTileVertices()){
                if (v.getElevation()<lowest_ele.getElevation()){
                    lowest_ele=v;
                }
                if (v.getElevation()>highest_ele.getElevation()){
                    highest_ele=v;
                }
            }
        }

        if (highest_ele.getTemperature()<lowest_ele.getTemperature()){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    //Testing whether the temperature of each tile is indeed the average.
    @Test
    public void TestAverageTemp(){
        boolean check=true;

        double currtemp;

        for (Tile t:tiles){
            currtemp=0.0;
            for (TileVertex v:t.getTileVertices()){
                currtemp+=v.getTemperature();
            }
            if (currtemp/t.getTileVertices().size()!=t.getAverageTemperature()){
                check=false;
            }
        }

        assertTrue(check);
    }
}
