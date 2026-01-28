package ca.mcmaster.cas.se2aa4.a2.generator.Mesh;

import ca.mcmaster.cas.se2aa4.a2.generator.CustomVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.CustomPolygon;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.GeneratePolygon;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.RegularPolygon;

import java.awt.Color;
import java.util.ArrayList;

public class RegularMesh extends MeshADT {
    int squareSize;
    CustomVertex[][] centroidVertice;
    CustomVertex[][] connectingVertice;

    public RegularMesh(int width, int height, int precision, int squareSize)
    {
        super(width, height, precision, width*height/squareSize^2);
        this.squareSize = squareSize;
        centroids=new ArrayList<>();
        vertices = new ArrayList<>();
        segments=new ArrayList<>();
        //Generates all centroids that Polygon will be built off.
        createCentroidVertices();

        //Create a polygon for each vertex
        for (int i=0; i<centroids.size(); i++){
            GeneratePolygon gen_poly=new RegularPolygon(i, squareSize);
            CustomPolygon polygon=new CustomPolygon(gen_poly);
            //Adds each polygon's vertices and segments to the overall list.
            addPolygon(polygon.getPolygon());
        }
    }

    public void createCentroidVertices(){
        //Create the centroid vertices for grid mesh: equally spaced out
        centroidVertice = new CustomVertex[width/squareSize][height/ squareSize];
        
        for(int x = 0 ; x*squareSize + squareSize/2 < width; x++) {
            for(int y = 0 ; y*squareSize + squareSize/2 < height; y ++) {
                double xPos = x*squareSize + squareSize/2;
                double yPos = y*squareSize + squareSize/2;
                CustomVertex new_v= new CustomVertex(xPos, yPos ,new Color(254,0,0,254), "2.0", precision);
                centroidVertice[x][y]=new_v;
                centroids.add(new_v);
            }
        }
    }

    public CustomVertex[][] getCentroidVertices(){
        return centroidVertice;
    }

    public CustomVertex getConnectingVertices(int x, int y){
        return connectingVertice[x][y];
    } 
}
