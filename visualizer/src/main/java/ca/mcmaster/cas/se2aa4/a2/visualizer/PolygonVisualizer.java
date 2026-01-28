package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.tools.ExtractPolygonInfo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;

public class PolygonVisualizer extends ExtractPolygonInfo implements colourExtraction{
    private Color polygonColor;
    private java.awt.geom.Path2D.Double poly;
    
    public  PolygonVisualizer(Polygon polygon, List<Segment> meshSegments, List<Vertex> meshVertex, int offset){
        super(polygon, meshSegments, meshVertex, offset);
        polygonColor = extractColor(polygon.getPropertiesList());
    }

    public Color getColor(){
        return polygonColor;
    }
    
    public Color extractColor(List<Property> properties) {
        //Get the colour for the vertex 
        String val = null;

        //Get the colours properties
        for(Property p: properties) {
            if (p.getKey().equals("background_color")) {
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

    public java.awt.geom.Path2D.Double getShape(){
        makeShape();
        return this.poly;

    }

    public void makeShape(){
        List<Vertex> polyVertex = new ArrayList<>();
        List<SegmentVisualizer> segmentVisuals = CanvasDrawer.segmentVisualsList;

        //Collect the vertices in order
        for (Integer pos: segmentIDs){
            SegmentVisualizer segmentVisual = segmentVisuals.get(pos);
            Vertex v1 = segmentVisual.getVertex1();
            Vertex v2 = segmentVisual.getVertex2();
            if (!segmentVisual.isReferenced()){
                if(!polyVertex.contains(v1)){
                    polyVertex.add(v1);
                }
                else{
                    polyVertex.add(v2);
                }
                segmentVisual.reference();
            }
            else{
                if(!polyVertex.contains(v2)){
                    polyVertex.add(v2);
                }
                else{
                    polyVertex.add(v1);
                }
            }
        }

        //convert struct Vertex to Coordinates
        Coordinate coordinates []= new Coordinate[polyVertex.size()];
        for (int i = 0; i < polyVertex.size(); i++){
            Vertex v = polyVertex.get(i);
            coordinates[i] = new Coordinate(v.getX(), v.getY());
        }

        //Create the closed polygon
        poly = new java.awt.geom.Path2D.Double();
        Coordinate coordinate = coordinates[0];
        poly.moveTo(coordinate.getX(), coordinate.getY());
        for (int i = 1; i < coordinates.length; i++){
            coordinate = coordinates[i];
            poly.lineTo(coordinate.getX(), coordinate.getY());
        }
        poly.closePath();
    }


    
}
