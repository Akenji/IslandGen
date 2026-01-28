package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class ExtractPolygonInfo {
    protected List<Integer> segmentIDs;
    protected List<Integer> neighbouringSegmentsID;
    protected List<Segment> segments;
    protected List<Segment> neighbouringSegments;
    private List<Segment> meshSegments;
    private List<Vertex> meshVertex;
    protected List<Vertex> vertices;
    private int numPolygons;

    public ExtractPolygonInfo(Polygon polygon, List<Segment> meshSegments, List<Vertex> meshVertex, int numPolygons){
        this.segmentIDs = polygon.getSegmentIdxsList();
        this.neighbouringSegmentsID = polygon.getNeighborIdxsList();
        this.numPolygons = numPolygons;
        this.meshSegments = meshSegments;
        this.meshVertex = meshVertex;
        this.segments = new ArrayList<>();
        this.neighbouringSegments = new ArrayList<>();
        this.vertices = new ArrayList<>();
        polygonSegment();
        polygonNeighbouringSegments();
        polygonVertices();
    }

    private void polygonSegment(){
        for (Integer pos: segmentIDs){
            segments.add(meshSegments.get(pos));
        }
    }

    private void polygonNeighbouringSegments(){
        for (Integer pos: neighbouringSegmentsID){
            neighbouringSegments.add(meshSegments.get(pos));
        }
    }

    private void polygonVertices(){
        for (Segment segment: segments){
            ExtractSegmentInfo segmentInfo = new ExtractSegmentInfo(segment, meshVertex, numPolygons);

            //Get the two vertices of the segment 
            Vertex v1 = segmentInfo.getVertex1();
            Vertex v2 = segmentInfo.getVertex2();

            if (!vertices.contains(v1)){
                vertices.add(v1);
            }

            if (!vertices.contains(v2)){
                vertices.add(v2);
            }
        }
    }

    public List<Integer> getSegmentsIds(){
        return segmentIDs;
    }

    public List<Integer> getNeighbouringSegmentsId(){
        return neighbouringSegmentsID;
    }

    public List<Segment> getSegment(){
        return segments;
    }
    public List<Vertex> getVertex(){
        return vertices;
    }

}
