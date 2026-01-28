package ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

import ca.mcmaster.cas.se2aa4.a2.generator.CustomSegments;
import ca.mcmaster.cas.se2aa4.a2.generator.CustomVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.MeshADT;

import java.util.ArrayList;
import java.util.List;

abstract public class GeneratePolygon {

    protected List<CustomVertex> centroids;
    protected List<CustomVertex> vertices;
    protected List<CustomSegments> segments;
    private int precision;


    protected CustomVertex centroid;

    protected Integer centroid_idx;

    protected List<CustomVertex> poly_vertices;
    protected List<CustomSegments> poly_segment;
    protected List<Integer> segment_index;
    protected List<Integer> neighbours;


    public GeneratePolygon(){
        this.precision= MeshADT.getPrecision();
        this.centroids=MeshADT.getCustomCentroids();
        this.vertices=MeshADT.getAllCustomVertices();
        this.segments=MeshADT.getCustomSegments();
    }

    abstract CustomSegments makeSegment(int v1, int v2);


    protected List<CustomSegments> makeSegments(List<CustomVertex> all_vertices){
        List<CustomSegments> curr_segments=new ArrayList<>();
        for (int i=1; i<all_vertices.size(); i++){
            CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(i-1)),vertices.indexOf(all_vertices.get(i)));
            curr_segments.add(s);
        }
        CustomSegments s=makeSegment(vertices.indexOf(all_vertices.get(0)),vertices.indexOf(all_vertices.get(all_vertices.size()-1)));
        curr_segments.add(s);
        return curr_segments;
    }

    public List<Integer> getSegmentIndex(List<CustomSegments> partSegments){
        List<Integer> indexes=new ArrayList<>();
        for (CustomSegments s: partSegments){
            indexes.add(segments.indexOf(s));
        }
        return indexes;
    }

    protected CustomVertex makeVertex(double x, double y){
        CustomVertex v=new CustomVertex(x,y,precision);

        for (CustomVertex c: vertices){
            if (c.getX()==v.getX() & c.getY()==v.getY()){
                return c;
            }
        }
        vertices.add(v);
        return v;
    }

    public List<CustomSegments> getAllSegments(){
        return this.poly_segment;
    }

    public List<CustomVertex> getAllVertices(){
        return this.poly_vertices;
    }

    public List<Integer> getAllNeighbours(){
        return this.neighbours;
    }

    public CustomVertex getCentroid(){
        return this.centroid;
    }

    public List<Integer> getAllSegmentIdx(){
        return this.segment_index;
    }

}
