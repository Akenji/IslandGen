package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.MeshType;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;


public class GeneratorTest {

    @Test
    public void overlapCheck() throws IOException{
        Generator generator = new Generator();
        int numberPolygons = 100;
        Structs.Mesh aMesh = generator.generate(numberPolygons, 500, 500, MeshType.GRID, 5, 20);

        int num_vertices=(26*26)+(25*25);
        int num_segments=(26*25)+(26*25)+(25*24)+(25*24);

        int actual_vertices=aMesh.getVerticesCount();
        int actual_segments=aMesh.getSegmentsCount();

        assertTrue(num_vertices==actual_vertices && num_segments==actual_segments);
    }

    @Test
    public void meshIsNotNull() throws IOException {
        Generator generator = new Generator();
        int numberPolygons = 100;
        Structs.Mesh aMesh = generator.generate(numberPolygons, 500, 500, MeshType.GRID, 5, 20);
        assertNotNull(aMesh);
    }

    @Test
    public void meshRightWidthRange() throws IOException {
        Generator generator = new Generator();
        int numberPolygons = 100;
        int width = 500;
        Structs.Mesh aMesh = generator.generate(numberPolygons, width, 500, MeshType.GRID, 5, 20);
        List<Vertex> vertices = aMesh.getVerticesList();
        for(Vertex vertex: vertices)
        {
            assertTrue(vertex.getX() <= width && vertex.getX() >= 0);
        }
    }

    @Test
    public void meshRightHeightRange() throws IOException {
        Generator generator = new Generator();
        int numberPolygons = 100;
        int height = 500;
        Structs.Mesh aMesh = generator.generate(numberPolygons, 500, height, MeshType.GRID, 5, 20);
        List<Vertex> vertices = aMesh.getVerticesList();
        for(Vertex vertex: vertices)
        {
            assertTrue(vertex.getX() <= height && vertex.getX() >= 0);
        }
    }

}
