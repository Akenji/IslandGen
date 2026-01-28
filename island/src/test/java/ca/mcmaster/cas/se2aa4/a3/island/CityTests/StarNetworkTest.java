package ca.mcmaster.cas.se2aa4.a3.island.CityTests;

//import ca.mcmaster.cas.se2aa4.a3.island.Networks.StarNetwork;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import ca.mcmaster.cas.se2aa4.a2.io.Structs;
//import Node;
//import UndirectedGraph;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StarNetworkTest {
//    @Test
//    public void testCreateStarNetwork() {
//        Graph graph = new Graph();
//
//        // Create a star network with 5 vertices
//        int numVertices = 5;
//        graph.createStarNetwork(numVertices);
//
//        // Verify that the graph has the correct number of vertices
//        assertEquals(numVertices, graph.getNumVertices());
//
//        // Verify that each vertex has a degree of 1 except for the center vertex
//        for (int i = 0; i < numVertices; i++) {
//            if (i == 0) {
//                assertEquals(numVertices - 1, graph.getDegree(i));
//            } else {
//                assertEquals(1, graph.getDegree(i));
//            }
//        }
//    }
//}
