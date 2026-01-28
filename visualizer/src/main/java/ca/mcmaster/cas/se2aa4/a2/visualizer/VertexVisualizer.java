package ca.mcmaster.cas.se2aa4.a2.visualizer;

import java.awt.Color;
import java.util.List;
import java.awt.geom.Ellipse2D;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractVertexInfo;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class VertexVisualizer extends ExtractVertexInfo implements colourExtraction{
    private double XCenter;
    private double YCenter;
    private Boolean drawn;


    private boolean isCentroid;
    Color vertexColor;

    public VertexVisualizer (Vertex vertex, Boolean debug, Boolean debugElevation, Boolean isCentroid)
    {
        super(vertex);
        this.drawn = false;
        this.isCentroid = isCentroid;

        this.XCenter = X - (thickness/2.0d);
        this.YCenter = Y - (thickness/2.0d);

        if (debugElevation && !isCentroid){
            this.vertexColor = Color.BLACK;
            this.thickness=Double.valueOf(vertex.getProperties(3).getValue())/100.0;
        }else if (debug && !isCentroid){
            this.vertexColor = Color.BLACK;
        }
        else if (isCentroid){
            this.vertexColor = extractColor(vertex.getPropertiesList());
            this.thickness = Double.valueOf(vertex.getProperties(1).getValue());
        }
        else{
            this.vertexColor = extractColor(vertex.getPropertiesList());
        }
    }

    public boolean isCentroid(){
            return isCentroid;
    }

    public Color getColor(){
        return vertexColor;
    }

    public boolean isDrawn()
    {
        return drawn;
    }

    public void drawn(){
        drawn = true; 
    }

    public Ellipse2D getPoint(){
        Ellipse2D point = new Ellipse2D.Double(XCenter, YCenter, thickness, thickness);

        return point;
    }

    public Color extractColor(List<Property> properties) {
        //Get the colour for the vertex 
        String val = null;

        //Get the colours properties
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }

        if (val == null)
            return Color.BLACK;

        //Extract the colour and transparency values
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int transparency = Integer.parseInt(raw[3]);

        return new Color(red, green, blue, transparency);
    }
}
