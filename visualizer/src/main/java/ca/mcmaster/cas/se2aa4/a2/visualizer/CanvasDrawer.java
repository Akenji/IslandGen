package ca.mcmaster.cas.se2aa4.a2.visualizer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import java.awt.BasicStroke;

public class CanvasDrawer {
    private Graphics2D canvas;
    private Boolean debugMode;
    private float defaultStrokeWidth;
    private Color canvasDefaultColor = Color.BLACK;
    private List<PolygonVisualizer> polygons;
    protected static List<SegmentVisualizer> segmentVisualsList;
    private List<VertexVisualizer> vertexVisualsList;

    public CanvasDrawer(Graphics2D canvas, Boolean debugMode){
        this.canvas = canvas;
        this.debugMode = debugMode;
        this.defaultStrokeWidth = 0.5f;
    }

    public void setCanvas(){
        canvas.setColor(Color.BLACK);
        resetStroke();
    }

    private void resetStroke(){
        Stroke stroke = new BasicStroke(defaultStrokeWidth);
        canvas.setStroke(stroke);
    }

    public void drawPolygons(List<PolygonVisualizer> polygons, List<SegmentVisualizer> segmentVisualsList, List<VertexVisualizer> vertexVisualsList){
        //Draw the Polygon one by one
        this.polygons = polygons;
        this.segmentVisualsList = segmentVisualsList;
        this.vertexVisualsList = vertexVisualsList;

        for (PolygonVisualizer polygon : polygons) {
            java.awt.geom.Path2D.Double poly = polygon.getShape();
            canvas.setColor(polygon.getColor());
            canvas.fill(poly);
            canvas.setColor(canvasDefaultColor);
        }

        //Print all segments for each polygon
        for (PolygonVisualizer polygon : polygons) {
            //print each segment of that polygon one by one 
            for (Integer i : polygon.getSegmentsIds()) {
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
                drawSegment(segmentVisual);
            }
        }

        //Debug mode outputs
        if (debugMode){
            for (PolygonVisualizer polygon: polygons){
                debugSegment(polygon);
            }
            for (int i = 0; i < polygons.size(); i++) {
                drawVertex(i);
            }
        }

        for(PolygonVisualizer polygon: polygons){
            for (Integer i : polygon.getNeighbouringSegmentsId()) {
                SegmentVisualizer segment = segmentVisualsList.get(i);
                if(segment.getThickness() > 0.5){ // only draw the neighbouring segment if it is a road segment (by default road segments have thickness of 2.0)
                    drawSegment(segment);
                }
            }
        }

        for (int i = 0; i < polygons.size(); i++){
            drawVertex(i);
        }

        //Print all Vertices for each polygon
        for (PolygonVisualizer polygon : polygons) {
            //print each segment of that polygon one by one 
            for (Integer i : polygon.getSegmentsIds()) {
                SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
                drawVertex(segmentVisual.getVertedIDX1());
                drawVertex(segmentVisual.getVertedIDX2());
            }
        }
    }
    private void drawSegment(SegmentVisualizer segmentVisual)
    {
        //Draws a segment if it is not drawn on canvas yet
        if (!segmentVisual.isDrawn()){
            canvas.setColor(segmentVisual.getColor());

            Vertex v1 = segmentVisual.getVertex1();
            Vertex v2 = segmentVisual.getVertex2();
    
            double x1 = v1.getX();
            double y1 = v1.getY();
            double x2 = v2.getX();
            double y2 = v2.getY();
            
            Line2D line = segmentVisual.getLine(x1, y1, x2, y2);

            canvas.setStroke(new BasicStroke((float)segmentVisual.getThickness()));
            canvas.draw(line);
            canvas.setColor(canvasDefaultColor);
            segmentVisual.draw();
            resetStroke();
        }
    }

    private void drawVertex (int pos){
        //Print vertex with no overlaps
        if (!vertexVisualsList.get(pos).isDrawn()) {
            VertexVisualizer vertexVisual = vertexVisualsList.get(pos);

            canvas.setColor(vertexVisual.getColor());
            canvas.fill(vertexVisual.getPoint());
            canvas.setColor(canvasDefaultColor);
            vertexVisual.drawn();
        }
    }

    private void debugSegment(PolygonVisualizer polygon){
        //Output debug elements
        for (Integer i : polygon.getNeighbouringSegmentsId()) {
            SegmentVisualizer segmentVisual = segmentVisualsList.get(i);
            drawSegment(segmentVisual);
        }
    }
    
}
