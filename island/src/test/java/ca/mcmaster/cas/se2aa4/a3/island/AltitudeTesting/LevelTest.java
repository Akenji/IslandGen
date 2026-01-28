package ca.mcmaster.cas.se2aa4.a3.island.AltitudeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevelTest extends SetUpTest {

    //Test checks to see if lowest land elevation is always 0.
    // Done to align land with ocean at some point in island. Creates more smooth transition.
    @Test
    public void TestLevelling(){

        double min=Double.MAX_VALUE;

        for (Tile t:tiles){
            if (t.isTileLand()){
                for (TileVertex v:t.getTileVertices()){
                    if (v.getElevation()<min){
                        min=v.getElevation();
                    }
                }
            }
        }

        assertTrue(min==0);

    }

}
