package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;

public class River extends BodiesWater {
    private List<TileVertex> riverVertices;
    private List<TileSegment> riverSegments;

    public River(TileVertex riverStart){
        riverVertices = new ArrayList<>();
        riverSegments = new ArrayList<>();
        riverVertices.add(riverStart);
        this.humidity_level=2.0;
    }

    public void addRiverVertex(TileVertex riverVertex){
        riverVertices.add(riverVertex);
    }
    
    public void addRiverSegment(TileSegment riverSegment){
        riverSegments.add(riverSegment);
    }

    public Boolean canAddVertexToRiver(TileVertex vertex){
        //ensures no duplicates in the river
        if (riverVertices.contains(vertex)){
            return false;
        }
        return true;
    }

    public TileSegment getRiverlastSegment(){
        return riverSegments.get(getRiverSize() - 1);
    }

    public int getRiverSize(){
        return riverSegments.size();
    }

    public List<TileVertex> getRiverVerticies(){
        return riverVertices;
    }

    public void setRiverAttributes(){
        //set the attributes for the river segments
        for (TileSegment segment: riverSegments){
            segment.setRiver();
            segment.setSegmentVertexRiver();
        }
    }
    public List<TileVertex> getMidPoints(){
        return riverVertices;
    }


    public Double getHumidityLevel() {
        return this.humidity_level;
    }

    public List<TileSegment> getRiverSegments(){
        return riverSegments;
    }
}
