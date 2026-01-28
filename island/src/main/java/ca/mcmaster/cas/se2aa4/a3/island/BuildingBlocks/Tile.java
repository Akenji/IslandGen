package ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks;

import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.LandComposition;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileElement;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractPolygonInfo;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class Tile extends ExtractPolygonInfo{
    TileVertex centroid;
    public TileTypes tileType;
    List<TileSegment> tileSegmentList;
    List<TileSegment> neighbouringTileSegmentList;
    List<TileVertex> tileVerticesList;
    List<Tile> neighbouringTileList;
    Color color = TileTypes.UNDETERMINEDLAND.getColor();
    Double averageTemperature;

    public Tile(Polygon polygon, List<Segment> meshSegments, List<Vertex> meshVertices, int numPolygons){
        super(polygon, meshSegments, meshVertices, numPolygons);
        this.tileType = TileTypes.UNDETERMINEDLAND;
        tileSegmentList = new ArrayList<>();
        neighbouringTileSegmentList = new ArrayList<>();
        tileVerticesList = new ArrayList<>();
        neighbouringTileList = new ArrayList<>();
    }

    public void setCentroid(TileVertex vertex){
        centroid = vertex;
    }

    public void addTileSegment(TileSegment tileSegment){
        tileSegmentList.add(tileSegment);
    }

    public void addNeighbouringTileSegment(TileSegment tileSegment){
        neighbouringTileSegmentList.add(tileSegment);
    }

    public void addTileVertex(TileVertex tileVertex){
        tileVerticesList.add(tileVertex);
    }

    public void setTileType(TileTypes tileType){
        this.tileType = tileType;
        this.color=tileType.getColor();

        //Set all the vertice to water element if tile element is water
        if (isTileWater()){
            for (TileVertex vertex: tileVerticesList){
                vertex.setVertexWater();
            }
        }
    }

    public Boolean isTileWater(){
       if (tileType.getElememt().equals(TileElement.WATER)){
            return true;
        }
        return false;
    }

    public Boolean isTileSoil(){
        if (tileType.composition== LandComposition.SOIL){
            return true;
        }
        return false;
    }

    public Boolean isTileLand(){
        if (tileType.getElememt().equals(TileElement.LAND)){
            return true;
        }
        return false;
    }
    public Boolean isTileOcean(){
        if (tileType.equals(TileTypes.Ocean)){
            return true;
        }
        return false;
    }

    public void setAverageTemperature(double temperature){
        this.averageTemperature=temperature;
    }

    public double getAverageTemperature() {
        return this.averageTemperature;
    }

    public Boolean isTileUndetermined(){
        if (tileType.getElememt().equals(TileElement.UNDETERMINED)){
            return true;
        }
        return false;
    }
    
    public TileVertex getCentroid(){
        return centroid;
    }

    public double getCentroidX(){
        return centroid.getX();
    }

    public double getCentroidY(){
        return centroid.getY();
    }

    public List<TileSegment> getNeighbouringTileSegments(){
        return neighbouringTileSegmentList;
    }
    
    public List<Tile> getNeighbouringTile(){
        return neighbouringTileList;
    }
    public int numNeighbouringTiles(){
        return neighbouringTileList.size();
    }

    public List<TileSegment> getTileSegments(){
        return tileSegmentList;
    }

    public Boolean isTileSegmentListContains(TileSegment tileSegment){
        if (tileSegmentList.contains(tileSegment)){
            return true;
        }
        return false;
    }

    public Boolean isTileVerticesListContains(TileVertex tileVertex){
        if (tileVerticesList.contains(tileVertex)){
            return true;
        }
        return false;
    }

    public TileVertex getTileVertex(int pos){
        return tileVerticesList.get(pos);
    }

    public int tileVerticesListSize(){
        return tileVerticesList.size();
    }

    public Polygon getPolygon(){
        //create the Struct Polygon of Tiles
        String colorCode = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
        Property colourProperty=Property.newBuilder().setKey("background_color").setValue(colorCode).build();
        return Polygon.newBuilder().addAllSegmentIdxs(this.segmentIDs).addAllNeighborIdxs(this.neighbouringSegmentsID).addProperties(0,colourProperty).build();
    }

    public List<TileVertex> getTileVertices(){
        return this.tileVerticesList;
    }
    public void addNeighbouringTile(Tile tile){
        neighbouringTileList.add(tile);
    }

    public void setPolygonColor(Color c){
        this.color=c;
    }

    public Color getColor(){
        return this.color;
    }
}
