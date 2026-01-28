package ca.mcmaster.cas.se2aa4.a2.generator.Mesh;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.generator.CustomSegments;
import ca.mcmaster.cas.se2aa4.a2.generator.CustomVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import org.locationtech.jts.geom.Coordinate;

public abstract class MeshADT {

    public List<Polygon> mesh;
    protected static int precision;
    protected static int height;
    protected static int width;
    protected static List<CustomVertex> vertices;
    protected int numPolygons;
    protected static List<Coordinate> collection_centroid;
    protected static List<CustomVertex> centroids;
    public static List<CustomSegments> segments;

    public MeshADT(){
        this.mesh=new ArrayList<>();
    }


    public MeshADT(int width, int height, int precision, int numPolygons){
        this.mesh = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.precision = precision;
        this.numPolygons = numPolygons;
    }

    //Returns list of polygon objects.
    public List<Polygon> getMesh(){
        return this.mesh;
    }

    //Adds individual polygons to the mesh consisting of all polygons.
    public void addPolygon(Polygon polygon){
        this.mesh.add(polygon);
    }
    
    public void addVertex(CustomVertex vertex)
    {
        vertices.add(vertex);
    }

    //Returns the Vertex equivalent List of CustomVertex.
    public List<Vertex> getVertices(){
        List<Vertex> reg_vertices=new ArrayList<>();

        for (CustomVertex v: vertices){
            reg_vertices.add(v.getVertex());
        }
        return reg_vertices;
    }


    public static List<CustomVertex> getAllCustomVertices(){
        return vertices;
    }

    public static List<CustomSegments> getCustomSegments(){
        return segments;
    }

    public static List<CustomVertex> getCustomCentroids(){
        return centroids;
    }


    //Returns the Segment equivalent of the List of CustomSegments.
    public List<Segment> getSegments(){
        List<Segment> reg_segments=new ArrayList<>();

        for (CustomSegments s: segments){
            reg_segments.add(s.getSegment());
        }
        return reg_segments;
    }

    //Returns the Vertex equivalent of all Centroids.
    public List<Vertex> getCentroids(){
        List<Vertex> reg_centroids=new ArrayList<>();

        for (CustomVertex v:centroids){
            reg_centroids.add(v.getVertex());
        }

        return reg_centroids;
    }

    public static int getPrecision(){
        return precision;
    }

    public static int getWidth()
    {
        return width;
    }
    
    public static int getHeight(){
        return height;
    }
}
