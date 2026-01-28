package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.awt.geom.AffineTransform;

public class Oval extends Shape{
    private double maxlength;
    private double length;
    private double shortLength;
    private double angleRad;
    private java.awt.Shape ellipse;
    
    public Oval (double width, double height, List<Tile> tiles){
        super();

        this.maxlength = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.length = IslandCommandLineReader.randomGenerator.getNextDouble(maxlength*0.7, maxlength);
        this.shortLength = IslandCommandLineReader.randomGenerator.getNextDouble(length*0.25,length*0.7);
        this.angleRad = IslandCommandLineReader.randomGenerator.getNextDouble(-Math.PI/2, Math.PI/2);
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        double xStartingPoint = meshCenterX - shortLength/2;
        double yStartingPoint = meshCenterY - length/2;
        
        java.awt.geom.Ellipse2D.Double oval = new java.awt.geom.Ellipse2D.Double(xStartingPoint, yStartingPoint, shortLength, length);
        this.ellipse = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(oval);
        markTiles();
    }

    public Oval (double width, double height, double angleRad, List<Tile> tiles){
        super();

        this.maxlength = Double.compare(width, height) < 0? width: height;
        this.tiles = tiles;
        this.length = IslandCommandLineReader.randomGenerator.getNextDouble(maxlength*0.45, maxlength*0.7);
        this.shortLength = IslandCommandLineReader.randomGenerator.getNextDouble(length*0.45,length*0.7);
        this.angleRad = angleRad;
        this.meshCenterX = width /2;
        this.meshCenterY = height /2;
        double xStartingPoint = meshCenterX - shortLength/2;
        double yStartingPoint = meshCenterY - length/2;
        
        java.awt.geom.Ellipse2D.Double oval = new java.awt.geom.Ellipse2D.Double(xStartingPoint, yStartingPoint, shortLength, length);
        this.ellipse = AffineTransform.getRotateInstance(angleRad,meshCenterX, meshCenterY).createTransformedShape(oval);
        markTiles();
    }

    public double getLength(){
        return length;
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
        //Any Tile's centroid that falls within the oval shape is considered as unmarked

        for (Tile tile: tiles){            
            if(ellipse.contains(tile.getCentroidX(), tile.getCentroidY())){
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
