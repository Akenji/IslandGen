package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;

public class CustomSegments {

    private int v1_idx;
    private int v2_idx;
    private Property colourProp;
    private Property thicknessProp;
    Property segmentType;
    //stores the struct.segment equivalent.
    private Segment segment;
    //stores the centroid that segment is associated with (if not a connecting segment)
    private Integer centroid;


    //Used to generate regular segments (not connecting).
    public CustomSegments(int v1_idx, int v2_idx, CustomVertex v1, CustomVertex v2, String thickness, Integer centroid) {
        this.v1_idx = v1_idx;
        this.v2_idx = v2_idx;
        this.colourProp = calcColor(v1, v2);
        this.thicknessProp = Property.newBuilder().setKey("thickness").setValue(thickness).build();
        this.segmentType = Property.newBuilder().setKey("segmentType").setValue("Regular").build();
        this.segment = Segment.newBuilder().setV1Idx(v1_idx).setV2Idx(v2_idx).addProperties(0, colourProp)
                .addProperties(1, thicknessProp).addProperties(2, segmentType).build();
        this.centroid = centroid;
    }

    //Used to generate neighbouring segments, where color is fixed.
    public CustomSegments(int v1_idx, int v2_idx, CustomVertex v1, CustomVertex v2, String thickness) {
        this.v1_idx = v1_idx;
        this.v2_idx = v2_idx;
        this.colourProp = setColour(Color.GRAY);
        this.thicknessProp = Property.newBuilder().setKey("thickness").setValue(thickness).build();
        this.segmentType = Property.newBuilder().setKey("segmentType").setValue("Neighbouring").build();
        this.segment = Segment.newBuilder().setV1Idx(v1_idx).setV2Idx(v2_idx).addProperties(0, colourProp)
                .addProperties(1, thicknessProp).addProperties(2, segmentType).build();
    }


    //Used to calculate the color for the segment.
    private Property calcColor(CustomVertex v1, CustomVertex v2) {
        Color c1 = v1.getColour();
        Color c2 = v2.getColour();
        int red = (c1.getRed() + c2.getRed()) / 2;
        int blue = (c1.getBlue() + c2.getBlue()) / 2;
        int green = (c1.getGreen() + c2.getGreen()) / 2;
        return setColour(new Color(red, green, blue));
    }

    //Converts color to Property value.
    private Property setColour(Color colour) {
        String colourCode = colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue() + "," + colour.getAlpha();
        return Property.newBuilder().setKey("rgb_color").setValue(colourCode).build();
    }

    public Segment getSegment() {
        return segment;
    }

    public Integer getCentroid() {
        return this.centroid;
    }

    public int getV1() {
        return this.v1_idx;
    }

    public int getV2() {
        return this.v2_idx;
    }
}
