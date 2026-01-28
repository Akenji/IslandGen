package ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.UndirectedGraph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CityGenerator implements Generator {
    private int numCities;
    private List<Land> landTiles;
    private UndirectedGraph<TileVertex> graph;
    private List<Node<TileVertex>> cityNodes;
    public CityGenerator(List<Land> allLand, int numCities) {
        this.numCities = numCities;
        this.landTiles = allLand;
        this.cityNodes = new ArrayList<>();
        this.graph = new UndirectedGraph<>();
    }

    public void generate() {
        // create the cities passing in nodes
        // add properties to the nodes (elevation, name of city, population, Tilevertex for city nodes)

        for (Land landTile : landTiles) { // add a node for each land tile to the graph
            Tile tile = landTile.getTile();
            TileVertex centroid = tile.getCentroid();
            Node<TileVertex> node = new Node<>(centroid);
            graph.addNode(node);
        }

        for (Land landTile : landTiles) { // create the graph edges for neighbouring centroids, graph checks for repeat edges
            Tile tile = landTile.getTile();
            TileVertex centroid = tile.getCentroid();
            List<Tile> neighbouringTiles = tile.getNeighbouringTile();
            for (Tile tile2 : neighbouringTiles) {
                if(tile2.isTileLand()){
                    TileVertex neighbourCentroid = tile2.getCentroid();
                    graph.addEdge(graph.getNode(centroid), graph.getNode(neighbourCentroid), calculateDistance(centroid, neighbourCentroid));
                }
            }
        }

        for (int i = 0; i < numCities; i++) {
            Node<TileVertex> cityNode;
            do {
                int randomNode = IslandCommandLineReader.randomGenerator.getNextInteger(0, graph.getNodes().size());
                cityNode = graph.getNodes().get(randomNode);
            } while(cityNode.hasProperty("City Type"));

            if(i == 0){ // first randomly selected node is set to be the capital city
                cityNode.setProperty("City Type", CityType.CAPITAL);
                cityNode.setProperty("Population", CityType.CAPITAL.getPopulation());
                cityNode.setProperty("Colour", new Color(255,0,0));
            }
            else{ // select the city types for the other cities and make sure they are not capital cities
                CityType[] types = CityType.values();
                CityType randomCityType;
                do {
                    randomCityType = types[IslandCommandLineReader.randomGenerator.getNextInteger(0, types.length)];
                } while(randomCityType.equals(CityType.CAPITAL));
                cityNode.setProperty("City Type", randomCityType);
                cityNode.setProperty("Population", randomCityType.getPopulation());
                cityNode.setProperty("Colour", new Color(0,0,0));
            }
            cityNodes.add(cityNode);
        }
    }

    private Double calculateDistance(TileVertex v1, TileVertex v2){
        double xDistance = v2.getX() - v1.getX();
        double yDistance = v2.getY() - v1.getY();
        double elevationDifference = v2.getElevation() - v1.getElevation();

        return Math.sqrt(xDistance*xDistance + yDistance*yDistance + elevationDifference*elevationDifference);
    }

    public List<Node<TileVertex>> getCities(){
        return cityNodes;
    }

    public UndirectedGraph<TileVertex> getGraph() {
        return graph;
    }
}
