package ca.mcmaster.cas.se2aa4.a3.island.Networks;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.CityType;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.Land;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathFinder.ShortestPath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.UndirectedGraph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StarNetwork {
    private UndirectedGraph<TileVertex> graph;
    private List<Node<TileVertex>> cities;
    private List<Node<TileVertex>> nodePath;
    private Node<TileVertex> centralHub;
    private List<TileVertex> cityCentroids;
    private List<TileVertex> pathTileCentroids;
    private List<Edge<TileVertex>> pathEdges;
    private List<Land> networkArea;
    public StarNetwork(UndirectedGraph<TileVertex> graph, List<Node<TileVertex>> cities, List<Land> landTiles){
        this.graph = graph;
        this.cities = cities;
        this.centralHub = null;
        this.cityCentroids = new ArrayList<>();
        this.pathEdges = new ArrayList<>();
        this.nodePath = new ArrayList<>();
        this.networkArea = landTiles;
        this.pathTileCentroids = new ArrayList<>();
    }

    public void createNetwork(){
        for(Node<TileVertex> city: cities){
            if(city.getPropertyValue("City Type").equals(CityType.CAPITAL)){
                centralHub = city; // extract the capital city from the list of cities
            }
        }
        cities.remove(centralHub);
        cityCentroids.add(centralHub.getId());


        for(Node<TileVertex> city: cities){
            ShortestPath<TileVertex> path = new ShortestPath<>();
            nodePath = path.findPath(graph, centralHub, city);

            if(nodePath != null){
                for(Node<TileVertex> node: nodePath){ // get the centroids that make up the shortest path
                    pathTileCentroids.add(node.getId());
                }
            }
            cityCentroids.add(city.getId());
            createPath(pathTileCentroids); // change the thickness of path edges
            pathTileCentroids.clear();
        }
        createCities(cityCentroids); // change the thickness of city vertices
    }

    private void createPath(List<TileVertex> pathCentroids){
        List<TileSegment> neighbouringSegments;
        for(Land landTile: networkArea){
            Tile tile = landTile.getTile();
            neighbouringSegments = tile.getNeighbouringTileSegments();
            for(TileSegment neighbouringSegment: neighbouringSegments){
                if(pathCentroids.contains(neighbouringSegment.getTileVertex1()) && pathCentroids.contains(neighbouringSegment.getTileVertex2())){
                    neighbouringSegment.setThickness(2.0);
                }
            }
        }
    }

    private void createCities(List<TileVertex> cityCentroids){
        for(Land landTile: networkArea){
            Tile tile = landTile.getTile();
            TileVertex tileCentroid = tile.getCentroid();
            for(Node<TileVertex> cityCentroid: cities){
                if(cityCentroid.getId().equals(tileCentroid)){
                    tileCentroid.setColor((Color) cityCentroid.getPropertyValue("Colour"));
                    tileCentroid.setThickness((int) cityCentroid.getPropertyValue("Population"));
                }
            }
            if(centralHub.getId().equals(tileCentroid)){
                tileCentroid.setColor((Color) centralHub.getPropertyValue("Colour"));
                tileCentroid.setThickness((int) centralHub.getPropertyValue("Population"));
            }
//            if(cityCentroids.contains(tileCentroid)){
//                tileCentroid.setColor();
//                tileCentroid.setThickness(10.0);
//            }
        }
    }

    public List<TileVertex> getCityCentroids(){
        return cityCentroids;
    }

    public List<Edge<TileVertex>> getPathEdges(){
        return pathEdges;
    }
}
