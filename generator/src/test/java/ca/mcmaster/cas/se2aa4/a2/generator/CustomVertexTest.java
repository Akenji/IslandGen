package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.RegularMesh;
import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.util.List;

public class CustomVertexTest {
    
    @Test
    public void vertexIsNotNull() {
        CustomVertex vertex = new CustomVertex(5, 2, 2);
        assertNotNull(vertex.getVertex());
    }

    @Test
    public void CentroidColour(){
        RegularMesh mesh = new RegularMesh(40, 40, 2, 20);
        for(CustomVertex centroid: mesh.getCustomCentroids()){
            assertEquals((new Color(254,0,0,254)).getRGB(), centroid.getColour().getRGB());
        }
    }
    
    @Test
    public void VertexTransparency(){
        CustomVertex vertex = new CustomVertex(0, 0, new Color(254, 254, 254, 254), "2", 2);
        Color vertexColor = vertex.getColour();
        assertNotNull(vertexColor.getTransparency());
    }

    @Test
    public void VertexThickness(){
        CustomVertex vertex = new CustomVertex(0, 0, new Color(254, 254, 254, 254), "2", 2);
        Vertex structVertex = vertex.getVertex();
        List<Property> properties = structVertex.getPropertiesList();
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }
        assertNotNull(val);
    }
}
