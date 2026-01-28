package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;



public class GraphicRenderer {
    public void render(Mesh aMesh, Graphics2D canvas, Boolean debug, Boolean debugElevation) throws Exception {
        //Set up the canvas
        CanvasDrawer canvasDrawer = new CanvasDrawer(canvas, debug);
        canvasDrawer.setCanvas();
        
        //Mesh information breakdown
        List<Polygon> polygons = aMesh.getPolygonsList();
        List<Segment> segments = aMesh.getSegmentsList();
        List<Vertex> vertex_list = aMesh.getVerticesList();

        //Polygons visualizing information
        List<PolygonVisualizer> polygonVisualsList = new ArrayList<>();
        for (Polygon polygon : polygons) {
            polygonVisualsList.add(new PolygonVisualizer(polygon, segments, vertex_list, polygons.size()));
        }

        //Segments visualizing information
        String type;
        List<SegmentVisualizer> segmentVisualsList = new ArrayList<>();
        for (Segment segment: segments){
            Property segmentType = segment.getProperties(2);
            type = segmentType.getValue();
            if (type.equals("Regular")){
                segmentVisualsList.add(new SegmentVisualizer(segment, debug, vertex_list, polygons.size()));
            }
            else if (type.equals("Neighbouring")){
                segmentVisualsList.add(new SegmentVisualizer(segment, debug, vertex_list, 0));
            }
            else{
                throw new Exception("Invalid segment type");
            }
        }

        //Vertices visualizing information
        List<VertexVisualizer> vertexVisualsList = new ArrayList<>();
        for(Vertex vertex : vertex_list){
            Property vertexType = vertex.getProperties(2);
            type = vertexType.getValue();
            if (type.equals("Regular")){
                vertexVisualsList.add(new VertexVisualizer(vertex, debug, debugElevation,false));
            }
            else if (type.equals("Centroid")){
                vertexVisualsList.add(new VertexVisualizer(vertex, debug, debugElevation,true));
            }
            else{
                throw new Exception("Invalid vertex type");
            }
        }

        canvasDrawer.drawPolygons(polygonVisualsList, segmentVisualsList, vertexVisualsList);
    }
}