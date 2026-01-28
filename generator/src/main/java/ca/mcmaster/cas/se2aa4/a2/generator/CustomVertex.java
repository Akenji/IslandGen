package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.MeshADT;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.util.*;
import java.awt.*;
import org.decimal4j.util.DoubleRounder;

public class CustomVertex {
    private double x;
    private double y;
    private static final String THICKNESSVALUE = "2.0";
    private Property colourProperty;
    private Property vertexType;
    private Vertex vertex;
    private Property thickness;
    private int height = MeshADT.getHeight();
    private int width = MeshADT.getWidth();


    //Constructor for regular vertices.
    public CustomVertex(double x, double y, int precision){
        //Constructor for centroid vertex
        this.x = DoubleRounder.round(x, precision);
        this.y = DoubleRounder.round(y, precision);
        this.colourProperty = randColor();
        this.thickness = Property.newBuilder().setKey("thickness").setValue(THICKNESSVALUE).build();
        this.vertexType = Property.newBuilder().setKey("vertexType").setValue("Regular").build();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(0,colourProperty).addProperties(1,thickness).addProperties(2,vertexType).build();
    }

    //Constructor for centroids.
    public CustomVertex(double x, double y, Color colour, String thicknessValue, int precision){
        //constructor for polygon vertex
        this.x = DoubleRounder.round(x, precision);
        this.y = DoubleRounder.round(y, precision);
        this.colourProperty = setColour(colour);
        this.thickness = Property.newBuilder().setKey("thickness").setValue(thicknessValue).build();
        this.vertexType = Property.newBuilder().setKey("vertexType").setValue("Centroid").build();
        this.vertex = Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(0,this.colourProperty).addProperties(1,thickness).addProperties(2,vertexType).build();
    }

    public Vertex getVertex(){
        return vertex;
    }

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }

    public void inMesh(){
        if (x > width) {
            x = width;
        } else if (x < 0) {
            x = 0;
        }
        if (y > height) {
            y = height;
        } else if (y < 0) {
            y = 0;
        }
    }


    public Color getColour(){
        //Gets the color of the vertex stored in the vertex struct
        String colourVal = colourProperty.getValue();

        if (colourVal == null)
        {
            return Color.BLACK;
        }

        String[] raw = colourVal.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        int transparency = Integer.parseInt(raw[3]);

        return new Color(red, green, blue, transparency);
    }

    private Property randColor(){
        //Randomly generate a colour for the vertex
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int transparency = bag.nextInt(155) + 100;
        String colorCode = red + "," + green + "," + blue + "," + transparency;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return color;    
    }


    private Property setColour(Color colour){
        //sets the color of the vertex to the vertex struct property
        String colorCode = colour.getRed() + "," + colour.getBlue() + "," + colour.getGreen() + "," + colour.getAlpha();
        Property colorProp = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        return colorProp;
    }
}
