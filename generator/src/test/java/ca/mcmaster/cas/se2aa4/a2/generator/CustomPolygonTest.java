package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.IrregularMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.RegularMesh;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.CustomPolygon;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.GeneratePolygon;
import ca.mcmaster.cas.se2aa4.a2.generator.Polygon.RegularPolygon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CustomPolygonTest {
    @Test
    public void consecutiveSegments() {
        //tests that the segments are stores in consecutive order
        RegularMesh mesh = new RegularMesh(40, 40, 2, 20);
        GeneratePolygon gen_poly=new RegularPolygon(0, 20);
        CustomPolygon polygon =new CustomPolygon(gen_poly);

        List<CustomSegments> segments = polygon.getPolySegments();

        for (int i = 0; i < segments.size() - 1; i++){
            CustomSegments s1 = segments.get(i);
            CustomSegments s2 = segments.get(i + 1);

            assertTrue(s1.getSegment().getV2Idx() == s2.getSegment().getV2Idx() || s1.getSegment().getV2Idx() == s2.getSegment().getV1Idx());
        }
    }

    @Test
    public void consecutiveSegmentsIrregular(){
        IrregularMesh mesh = new IrregularMesh(40, 40, 2, 10, 3);
        List<CustomVertex> cusVertices = new ArrayList<>();
        cusVertices.add(new CustomVertex(2, 2, 2));
        cusVertices.add(new CustomVertex(3, 2, 2));
        cusVertices.add(new CustomVertex(2, 3, 2));
        cusVertices.add(new CustomVertex(5, 10, 2));
        cusVertices.add(new CustomVertex(4, 8, 2));
        GeneratePolygon gen_poly=new RegularPolygon(0, 20);
        CustomPolygon polygon =new CustomPolygon(gen_poly);

        List<CustomSegments> segments = polygon.getPolySegments();

        for (int i = 0; i < segments.size() - 1; i++){
            CustomSegments s1 = segments.get(i);
            CustomSegments s2 = segments.get(i + 1);
            assert(s1.getSegment().getV2Idx() == s2.getSegment().getV2Idx() || s1.getSegment().getV2Idx() == s2.getSegment().getV1Idx());
        }
    }

}
