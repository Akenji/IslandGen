package ca.mcmaster.cas.se2aa4.a3.island.ShapeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.RandomShape;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RandomShapeTest extends SetUpTest{
    @Test
    public void randomislandCreatedTest(){
        //random Shape will always generate an island because it picks atleast 6 points on mesh to create a random shape to make an island
        //check will see if there are atleast 1 tile for the island
        List<Tile> tilesData = new ArrayList<>(tiles);
        RandomShape random = new RandomShape (width, height, tilesData);
        
        assertTrue(random.getInRangeTiles().size() >= 1);
    }
    
}
