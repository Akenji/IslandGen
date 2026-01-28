package ca.mcmaster.cas.se2aa4.a2.generator.Polygon;

import ca.mcmaster.cas.se2aa4.a2.generator.CustomSegments;
import ca.mcmaster.cas.se2aa4.a2.generator.CustomVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularPolygon extends GeneratePolygon {


    public RegularPolygon(int centroid, int squareSize){
        this.neighbours=new ArrayList<>();
        this.centroid_idx=centroid;
        this.centroid=centroids.get(centroid);


        this.poly_vertices= makeRegVertices(squareSize, centroids.get(centroid));

        this.poly_segment=makeSegments(poly_vertices);


        this.segment_index=getSegmentIndex(this.poly_segment);
    }

    private List<CustomVertex> makeRegVertices(int squareSize, CustomVertex centroid){
        double offset = squareSize / 2;

        CustomVertex v1=makeVertex(centroid.getX()-offset, centroid.getY()-offset);
        CustomVertex v2=makeVertex(centroid.getX()+offset, centroid.getY()-offset);
        CustomVertex v3=makeVertex(centroid.getX()+offset, centroid.getY()+offset);
        CustomVertex v4=makeVertex(centroid.getX()-offset, centroid.getY()+offset);


        return Arrays.asList(v1,v2,v3,v4);
    }

    protected CustomSegments makeSegment(int v1, int v2){
        CustomSegments s=new CustomSegments(v1,v2,vertices.get(v1),vertices.get(v2), "0.5f", this.centroid_idx);
        for (CustomSegments c: segments){
            //If segment already made, then this polygon is neighbour to the one whose segment is already made. For Regular Polygon only.
            if ((c.getV1() ==v1 & c.getV2() ==v2 | c.getV2() ==v1 & c.getV1() ==v2)){
                CustomSegments new_s=new CustomSegments(this.centroid_idx,c.getCentroid(),this.centroid, centroids.get(c.getCentroid()), "0.5f");
                segments.add(new_s);
                this.neighbours.add(segments.indexOf(new_s));

                return c;
            }
        }
        segments.add(s);
        return s;
    }


}
