package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.List;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

public class RandomShape extends Shape{
    private int maxPoints;
    private Coordinate coordinates [];
    private java.awt.geom.Path2D.Double irregularShape;
    
    public RandomShape (double width, double height, List<Tile> tiles){
        super();
        
        this.maxPoints = (int)IslandCommandLineReader.randomGenerator.getNextDouble(6, 25);
        this.tiles = tiles;
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;

        //Randomly generate potential vertices of the irregular polygon
        coordinates = new Coordinate[maxPoints];
        randomizeCoordinates(width, height);
        convexHull();
        markTiles();
    }

    private void randomizeCoordinates(double xUppderBound, double yUppderBound){
        double xCoordinate;
        double yCoordinate;

        for (int i = 0; i < maxPoints; i++){
            xCoordinate = IslandCommandLineReader.randomGenerator.getNextDouble(0, xUppderBound);
            yCoordinate = IslandCommandLineReader.randomGenerator.getNextDouble(0, yUppderBound);

            coordinates[i] = new Coordinate(xCoordinate, yCoordinate);
        }


    }

    private void convexHull(){
        GeometryFactory geometryFactory = new GeometryFactory();
        ConvexHull convexHull = new ConvexHull(coordinates, geometryFactory);
        Geometry geo = convexHull.getConvexHull();
        coordinates = geo.getCoordinates();

        //Create the closed polygon
        irregularShape = new java.awt.geom.Path2D.Double();
        Coordinate coordinate = coordinates[0];
        irregularShape.moveTo(coordinate.getX(), coordinate.getY());
        for (int i = 1; i < coordinates.length; i++){
            coordinate = coordinates[i];
            irregularShape.lineTo(coordinate.getX(), coordinate.getY());
        }
        irregularShape.closePath();

    }

    public List<Tile> getOutOfRangeTiles(){
        return outOfRangeTiles;
    }
    public List<Tile> getInRangeTiles(){
        return inRangeTiles;
    }

    public void markTiles(){
        //Any Tile's centroid that falls within the oval shape is considered as unmarked

        for (Tile tile: tiles){            
            if(irregularShape.contains(tile.getCentroidX(), tile.getCentroidY())){
                //in range
                inRangeTiles.add(tile);
            }
            else{
                //out of range
                outOfRangeTiles.add(tile);
            }
        }

    }
}
