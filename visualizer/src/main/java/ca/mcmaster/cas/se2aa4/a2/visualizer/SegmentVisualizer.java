package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractSegmentInfo;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

public class SegmentVisualizer extends ExtractSegmentInfo implements colourExtraction {
    private Boolean drawn;
    private Boolean referenced;
    private Color segmentColor;
    private String segmentType;

    public SegmentVisualizer(Segment segment, Boolean debug, List<Vertex> meshVertex, int offset) 
    {
        super(segment,meshVertex,offset);
        this.drawn = false;
        this.referenced = false;
        this.segmentType = segment.getProperties(2).getValue();

        if (debug && !segmentType.equals("Neighbouring"))
        {
            segmentColor = Color.BLACK;
        }
        else if(segmentType.equals("Neighbouring"))
        {
            segmentColor = Color.BLACK;
            this.thickness = Double.valueOf(segment.getProperties(1).getValue());
        }
        else
        {
            segmentColor = extractColor(segment.getPropertiesList());
        }
    }

    public boolean isDrawn()
    {
        return drawn;
    }
    public void draw(){
        drawn = true;
    }

    public boolean isReferenced()
    {
        return this.referenced;
    }
    public void reference(){
        this.referenced = true;
    }

    public Color getColor(){
        return segmentColor;
    }

    public double getThickness(){
        return thickness;
    }

    public Line2D getLine(double x1, double y1, double x2, double y2){
        Line2D line = new Line2D.Double(x1, y1, x2, y2);
        return line;
    }

    public Color extractColor(List<Property> properties) {
        String val = null;

        //Get the colour properties of the lines
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }

        if (val == null)
            return Color.BLACK;

        //Set the RGB values 
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int transparency = Integer.parseInt(raw[3]);

        return new Color(red, green, blue, transparency);
    }
    
}
