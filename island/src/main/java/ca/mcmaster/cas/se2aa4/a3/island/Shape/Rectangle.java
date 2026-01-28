package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.awt.geom.AffineTransform;
import java.util.List;


public class Rectangle extends Shape{
    private double maxsize;
    private double rectangleWidth;
    private double rectangleHeight;
    private double angleRad;
    private java.awt.Shape rectangle;
    
    public Rectangle (double width, double height, List<Tile> tiles){
        super();

        this.maxsize = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.rectangleWidth = IslandCommandLineReader.randomGenerator.getNextDouble(maxsize*0.5,maxsize*0.85);
        this.rectangleHeight = IslandCommandLineReader.randomGenerator.getNextDouble(maxsize*0.5,maxsize*0.85);
        this.angleRad = IslandCommandLineReader.randomGenerator.getNextDouble(0, Math.PI/2);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        int xStartingPoint = (int)(meshCenterX - rectangleWidth / 2);
        int yStartingPoint = (int)(meshCenterY - rectangleHeight / 2);
        
        java.awt.Rectangle basicRectangle = new java.awt.Rectangle(xStartingPoint, yStartingPoint, (int)rectangleWidth, (int)rectangleHeight);
        this.rectangle  = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(basicRectangle);
        markTiles();
    }

    public Rectangle (double width, double height, double angleRad, List<Tile> tiles){
        super();

        this.maxsize = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.rectangleWidth = IslandCommandLineReader.randomGenerator.getNextDouble(maxsize*0.15,maxsize*0.4);
        this.rectangleHeight = IslandCommandLineReader.randomGenerator.getNextDouble(maxsize*0.15,maxsize*0.4);
        this.angleRad = angleRad;
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        int xStartingPoint = (int)(meshCenterX - rectangleWidth / 2);
        int yStartingPoint = (int)(meshCenterY - rectangleHeight / 2);
        
        java.awt.Rectangle basicRectangle = new java.awt.Rectangle(xStartingPoint, yStartingPoint, (int)rectangleWidth, (int)rectangleHeight);
        this.rectangle  = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(basicRectangle);
        markTiles();
    }

    public double getMaxSize(){
        return maxsize;
    }

    public double getAngleRad(){
        return angleRad;
    }

    public List<Tile> getOutOfRangeTiles(){
        return outOfRangeTiles;
    }
    public List<Tile> getInRangeTiles(){
        return inRangeTiles;
    }
    public void markTiles(){
        //Any Tile's centroid that falls within the rectangle is considered as unmarked

        for (Tile tile: tiles){
            if(rectangle.contains(tile.getCentroidX(), tile.getCentroidY())){
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
