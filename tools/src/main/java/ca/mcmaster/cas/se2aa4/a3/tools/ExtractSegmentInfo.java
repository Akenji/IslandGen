package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class ExtractSegmentInfo {
    protected int vertex1ID;
    protected int vertex2ID;
    protected double thickness;
    protected Vertex v1;
    protected Vertex v2;

    public ExtractSegmentInfo (Segment segment, List<Vertex> meshVertex, int offset){
        this.vertex1ID = segment.getV1Idx() + offset;
        this.vertex2ID = segment.getV2Idx() + offset;
        this.thickness = extractThickness(segment.getPropertiesList());
        this.v1 = meshVertex.get(vertex1ID);
        this.v2 = meshVertex.get(vertex2ID);
    }

    public int getVertedIDX1(){
        return vertex1ID;
    }

    public int getVertedIDX2(){
        return vertex2ID;
    }

    public Vertex getVertex1(){
        return v1;
    }

    public Vertex getVertex2(){
        return v2;
    }

    private double extractThickness(List<Property> properties) {
        //Get the "thickness" of the vertex (size)
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return 0;
        return Double.parseDouble(val);
    }
}
