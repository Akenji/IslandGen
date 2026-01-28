package ca.mcmaster.cas.se2aa4.a3.island.LakeTesting;

import static org.junit.jupiter.api.Assertions.*;


import ca.mcmaster.cas.se2aa4.a3.island.Altitude.Altitude;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.Lake;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.LakeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LakeTest extends SetUpTest {

    @Test
    public void lakeSizeTest(){
        // checks whether a valid number of lakes have been generated
        List<Tile> copy= new ArrayList<>(tiles);
        LakeGenerator lakeGenerator = new LakeGenerator(10, copy);
        lakeGenerator.generate();

        List<Lake> lakes = lakeGenerator.getLakes();
        assertTrue(lakes.size() <= 10);
    }

    @Test
    public void lakeMergeTest(){
        // checks if there are any two lakes that merge (shared tiles)
        List<Tile> copy= new ArrayList<>(tiles);
        LakeGenerator lakeGenerator = new LakeGenerator(25, copy);
        lakeGenerator.generate();
        List<Lake> lakes = lakeGenerator.getLakes();

        for(int i = 0; i < lakes.size() - 1; i++){
            Lake compareLake = lakes.get(i);
            List<Tile> compareTiles = compareLake.getLakeTiles();
            for(int j = i+1; j < lakes.size() - 1; j++){
                Lake newLake = lakes.get(j);
                List<Tile> newTiles = newLake.getLakeTiles();
                assertTrue(Collections.disjoint(compareTiles, newTiles));
            }
        }
    }


}
