package ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

import ca.mcmaster.cas.se2aa4.a2.generator.CustomSegments;
import ca.mcmaster.cas.se2aa4.a2.generator.CustomVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;

public class CustomPolygon{

    private List<CustomVertex> poly_vertices;
    private List<CustomSegments> poly_segment;
    private List<Integer> segment_index;
    //Stores the indexes of segments that represent neighbour relation.
    private List<Integer> neighbours;
    private CustomVertex centroid;
    //Determines whether polygon is regular or not.
    private Polygon polygon;
    //Gets the list of all current centroids, vertices and segments from MeshADT.

    protected String colorCode="0,0,0,0";
    protected Property colourProperty=Property.newBuilder().setKey("background_color").setValue(colorCode).build();



    //Constructor for regular polygons.


    public CustomPolygon(GeneratePolygon gen_poly){
        this.centroid=gen_poly.getCentroid();

        this.neighbours=gen_poly.getAllNeighbours();

        this.poly_vertices=gen_poly.getAllVertices();

        this.poly_segment=gen_poly.getAllSegments();

        this.segment_index=gen_poly.getAllSegmentIdx();

        this.polygon=makePolygon();
    }




    //Creates and returns copy of polygon in type struct.Polygon.
    private Polygon makePolygon(){
        return Polygon.newBuilder().addAllSegmentIdxs(this.segment_index).addAllNeighborIdxs(this.neighbours).addProperties(0,colourProperty).build();
    }


    public CustomVertex getCentroid(){
        return this.centroid;
    }

    public List<CustomSegments> getPolySegments(){
        return this.poly_segment;
    }

    public List<CustomVertex> getPolyVertices(){
        return this.poly_vertices;
    }

    public Polygon getPolygon(){
        return polygon;
    }
}

