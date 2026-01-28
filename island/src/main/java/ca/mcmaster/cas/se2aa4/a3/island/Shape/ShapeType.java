package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.List;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

public enum ShapeType {
    CIRCLE("circle"),
    RECTANGLE("rectangle"), 
    OVAL("oval"), 
    RANDOM("random"), 
    IRREGULAR("irregular");

    private String name;

    private ShapeType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public Shape getShape(double width, double height,  List<Tile> tiles){
        switch(this){
            case CIRCLE :
                return new Circle(width, height, tiles);
            case RECTANGLE :
                return new Rectangle(width, height, tiles);
            case OVAL :
                return new Oval(width, height, tiles);
            case RANDOM :
                return new RandomShape(width, height, tiles);
            case IRREGULAR :
                return new IrregularShape(width, height, tiles);
            }
            return null;
    }
}
