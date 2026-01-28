package ca.mcmaster.cas.se2aa4.a3.island.ShapeTesting;

import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Circle;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CircleTest extends SetUpTest {
    //Test cases for circle shape
    @Test
    public void CircleInsideRadiusTest(){
        //Checks if all the in range tiles are within the circle radius
        List<Tile> tilesData = new ArrayList<>(tiles);
        Circle circle = new Circle (width, height, tilesData);

        double radius = circle.getRadius();

        double xDifference;
        double yDifference;
        double distance;
        
        for (Tile tile: circle.getInRangeTiles()){
            xDifference = tile.getCentroidX() - circle.getCenterX();
            yDifference = tile.getCentroidY() - circle.getCenterY();
            distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference, 2));
            assertTrue(distance < radius);
        }
    }

    @Test
    public void CircleOutsideRadiusTest(){
        //Checks if all the out of range tiles are outside the circle radius
        List<Tile> tilesData = new ArrayList<>(tiles);
        Circle circle = new Circle (width, height, tilesData);

        double radius = circle.getRadius();

        double xDifference;
        double yDifference;
        double distance;
        
        for (Tile tile: circle.getOutOfRangeTiles()){
            xDifference = tile.getCentroidX() - circle.getCenterX();
            yDifference = tile.getCentroidY() - circle.getCenterY();
            distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference, 2));
            assertTrue(distance >= radius);
        }
    }

    @Test
    public void CircleInsideRadiusOffsetTest(){
        //Checks if all the in range tiles are within the circle radius with different center
        List<Tile> tilesData = new ArrayList<>(tiles);
        Circle circle = new Circle (width*0.25, height*0.25, width *0.4, height *0.4, tilesData);

        double radius = circle.getRadius();

        double xDifference;
        double yDifference;
        double distance;
        
        for (Tile tile: circle.getInRangeTiles()){
            xDifference = tile.getCentroidX() - circle.getCenterX();
            yDifference = tile.getCentroidY() - circle.getCenterY();
            distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference, 2));
            assertTrue(distance < radius);
        }
    }

    @Test
    public void CircleOutsideRadiusOffsetTest(){
        //Checks if all the out of range tiles are outside the circle radius with different center
        List<Tile> tilesData = new ArrayList<>(tiles);
        Circle circle = new Circle (width*0.25, height*0.25, width *0.4, height *0.4, tilesData);

        double radius = circle.getRadius();

        double xDifference;
        double yDifference;
        double distance;
        
        for (Tile tile: circle.getOutOfRangeTiles()){
            xDifference = tile.getCentroidX() - circle.getCenterX();
            yDifference = tile.getCentroidY() - circle.getCenterY();
            distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference, 2));
            assertTrue(distance >= radius);
        }
    }
}

