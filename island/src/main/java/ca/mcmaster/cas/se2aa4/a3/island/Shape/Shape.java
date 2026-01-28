package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;
import java.util.ArrayList;

public abstract class Shape {
    protected double meshCenterX;
    protected double meshCenterY;
    protected List<Tile> tiles;
    protected List<Tile> inRangeTiles;
    protected List<Tile> outOfRangeTiles;
    public Shape(){
        inRangeTiles = new ArrayList<>();
        outOfRangeTiles = new ArrayList<>();
    }
    public abstract List<Tile> getOutOfRangeTiles();
    public abstract List<Tile> getInRangeTiles();
    public abstract void markTiles();
}
