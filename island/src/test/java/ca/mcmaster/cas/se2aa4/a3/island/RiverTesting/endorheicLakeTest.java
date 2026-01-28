package ca.mcmaster.cas.se2aa4.a3.island.RiverTesting;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.Lake;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.River;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.RiverGenerator;

public class endorheicLakeTest extends SetUpTest{
    @Test
    public void endorheicLakeBesideOceanTest(){
        //tests to see if the endorheic lake is not beside an ocean

        RiverGenerator riverGenerator = new RiverGenerator(tiles, 10);
        List<Lake> endorheicLakes = riverGenerator.getEndorheicLakes();

        for (Lake lake: endorheicLakes){
            for (Tile tile: lake.getLakeTiles()){
                for (Tile neighbour: tile.getNeighbouringTile()){
                    assertTrue(!neighbour.isTileOcean());
                }
            }
        }
    }

    @Test
    public void endorheicLakeLowestPointTest(){
        //tests to see if the endorheic lake is at the lowest point at the river

        RiverGenerator riverGenerator = new RiverGenerator(tiles, 10);
        List<Lake> endorheicLakes = riverGenerator.getEndorheicLakes();
        List<River> rivers = riverGenerator.getRivers();
        TileVertex lastRiverVertex;
        Boolean trigger;
        for (Lake lake: endorheicLakes){
            trigger = false;
            for (Tile tile: lake.getLakeTiles()){
                for (River river: rivers){
                    lastRiverVertex = river.getRiverVerticies().get(river.getRiverSize() - 1);
                    if (tile.isTileVerticesListContains(lastRiverVertex)){
                        trigger = true;
                        break;
                    }
                }
            }
            assertTrue(trigger);
        }

    }
}
