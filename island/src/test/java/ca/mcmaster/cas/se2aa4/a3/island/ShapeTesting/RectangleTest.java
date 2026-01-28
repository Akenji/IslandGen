package ca.mcmaster.cas.se2aa4.a3.island.ShapeTesting;

import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest extends SetUpTest{
    @Test
    public void RectangleInsideRadiusTest(){
        //Checks if the inrange tile and within the max borders
        List<Tile> tilesData = new ArrayList<>(tiles);
        Rectangle rectangle = new Rectangle (width, height, 0, tilesData);

        double maxsize = Double.compare(width, height) < 0? width: height;
        double length = maxsize*0.45;
        double xCenter = width / 2;
        double yCenter = height / 2;
        
        for (Tile tile: rectangle.getInRangeTiles()){
            assertTrue(tile.getCentroidX() < xCenter + length || tile.getCentroidX() > xCenter - length);
            assertTrue(tile.getCentroidY() < yCenter + length || tile.getCentroidY() > yCenter - length);
        }
    }

    @Test
    public void RectangleInsideRadiusOffsetTest(){
        //Checks if the inrange tile and within the max borders with different angle
        List<Tile> tilesData = new ArrayList<>(tiles);
        Rectangle rectangle = new Rectangle (width*0.6, height*0.6, Math.PI/4 ,tilesData);

        double maxsize = Double.compare(width, height) < 0? width*0.6: height*0.6;
        double length = maxsize*0.41*Math.sqrt(2);
        double xCenter = width / 2;
        double yCenter = height / 2;
        
        for (Tile tile: rectangle.getInRangeTiles()){
            assertTrue(tile.getCentroidX() < xCenter + length || tile.getCentroidX() > xCenter - length);
            assertTrue(tile.getCentroidY() < yCenter + length || tile.getCentroidY() > yCenter - length);
        }
    }

    
}
