package ca.mcmaster.cas.se2aa4.a3.island.ShapeTesting;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Oval;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OvalTest extends SetUpTest{
    @Test
    public void OvalInsideRadiusTest(){
        //Checks if the inrange tile and within the max borders
        List<Tile> tilesData = new ArrayList<>(tiles);
        Oval oval = new Oval (width, height, 0, tilesData);

        double maxsize = Double.compare(width, height) < 0? width: height;
        double length = maxsize * 0.71;
        double xCenter = width / 2;
        double yCenter = height / 2;
        
        for (Tile tile: oval.getInRangeTiles()){
            assertTrue(tile.getCentroidX() < xCenter + length || tile.getCentroidX() > xCenter - length);
            assertTrue(tile.getCentroidY() < yCenter + length || tile.getCentroidY() > yCenter - length);
        }
    }

    @Test
    public void OvalInsideRadiusOffsetTest(){
        //Checks if the inrange tile and within the max borders on different angle
        List<Tile> tilesData = new ArrayList<>(tiles);
        Oval oval = new Oval (width, height, Math.PI/4, tilesData);

        double maxsize = Double.compare(width, height) < 0? width: height;
        double length = maxsize * 0.71;
        double xCenter = width / 2;
        double yCenter = height / 2;
        
        for (Tile tile: oval.getInRangeTiles()){
            assertTrue(tile.getCentroidX() < xCenter + length || tile.getCentroidX() > xCenter - length);
            assertTrue(tile.getCentroidY() < yCenter + length || tile.getCentroidY() > yCenter - length);
        }
    }
    
}
