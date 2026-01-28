package ca.mcmaster.cas.se2aa4.a3.tools;

import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class ExtractVertexInfo {
    protected double X;
    protected double Y;
    protected double thickness; 

    public ExtractVertexInfo(Vertex vertex){
        this.X = vertex.getX();
        this.Y = vertex.getY();
        thickness = extractThickness(vertex.getPropertiesList());
    }

    public double getX(){
        return X;
    }

    public double getY(){
        return Y;
    }

    public double getThickness(){
        return thickness;
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
