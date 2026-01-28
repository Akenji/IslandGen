package ca.mcmaster.cas.se2aa4.a3.island.AltitudeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaterTest extends SetUpTest {

    //Checks to see if all the lakes are levelled to a specific elevation.
    @Test
    public void checkLevelledLakes(){
        double min_elevation=Double.MAX_VALUE;

        HashMap<Double, Integer> all_elevation=new HashMap<>();

        for (Tile t:tiles){
            if (t.isTileWater()){
                for (TileVertex v:t.getTileVertices()){
                    if (all_elevation.containsKey(v.getElevation())){
                        all_elevation.put(v.getElevation(),all_elevation.get(v.getElevation())+1);
                    }else if(v.getElevation()>0){
                        all_elevation.put(v.getElevation(),1);
                    }
                }
            }
        }

        boolean check=false;

        for (Double key: all_elevation.keySet()){
            if (all_elevation.get(key)>0){
                check=true;
            }
        }
        assertTrue(check);

    }

}
