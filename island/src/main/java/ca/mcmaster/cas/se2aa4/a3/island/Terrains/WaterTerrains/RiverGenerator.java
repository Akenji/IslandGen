package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class RiverGenerator implements Generator{
    private int maxNumRivers;
    private List<Tile> tiles;
    private List<River> rivers;
    private List<Lake> endorheicLake_list;
    private Boolean validRiver;


    public RiverGenerator(List<Tile> tiles, int maxRivers){
        this.tiles = new ArrayList<>(tiles);
        this.maxNumRivers = maxRivers;
        this.rivers = new ArrayList<>();
        this.endorheicLake_list = new ArrayList<>();
    }

    public void generate(){
        Boolean riverStartFound;
        TileVertex riverStart = null;
        Tile currentTile = null;
        River river;

        for (int i = 0; i < maxNumRivers; i++){
            riverStartFound = false;

            //Find a valid starting point for the river
            while (!riverStartFound)
            {
                currentTile = tiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0,tiles.size()));
                if (currentTile.isTileUndetermined() || currentTile.isTileLand()){
                    int pos = IslandCommandLineReader.randomGenerator.getNextInteger(0,currentTile.tileVerticesListSize());
                    TileVertex consideringVertex = currentTile.getTileVertex(pos);
                    if (consideringVertex.isVertexLand()){
                        riverStart = consideringVertex;
                        riverStartFound = true;
                    }
                }
            }

            river = new River(riverStart);
            validRiver = true;
            extendRiver(riverStart, currentTile, river);

            if (validRiver){
                river.setRiverAttributes();
                rivers.add(river);
            }
            
        }
    }

    public void extendRiver(TileVertex riverStart, Tile currentTile, River river){
        //extend the river
        TileVertex previousRiverVertex  = riverStart;
        TileVertex possibleRiverVertex;
        TileSegment possibleRiverSegment;
        Tile possibleAdjacentRiverTile;
        Boolean waterFlowFound;
        double minElevation = previousRiverVertex.getElevation();

        while(previousRiverVertex.isVertexLand()){
            possibleRiverVertex = null;
            possibleRiverSegment = null;
            possibleAdjacentRiverTile = null;
            waterFlowFound = false;

            //Find all the possible segments from neighbouring polygons and check if their adjacent vertex is lower than previousRiverVertex
            for(Tile tile: currentTile.getNeighbouringTile()){
                for (TileSegment segment: tile.getTileSegments()){
                    if (segment.containsTileVertex(previousRiverVertex)){
                        TileVertex adjacentVertex = segment.getAdjacentVertex(previousRiverVertex);
                        if (adjacentVertex.getElevation() < minElevation && river.canAddVertexToRiver(adjacentVertex)){
                            minElevation = adjacentVertex.getElevation();
                            possibleRiverVertex = adjacentVertex;
                            possibleRiverSegment = segment;
                            possibleAdjacentRiverTile = tile;
                            waterFlowFound = true;
                        }
                    }
                }
            }

            //Add the next sequence of segment and vertex to the river
            if (waterFlowFound){
                river.addRiverSegment(possibleRiverSegment);
                river.addRiverVertex(possibleRiverVertex);
                previousRiverVertex = possibleRiverVertex;
                currentTile = possibleAdjacentRiverTile;
            }
            else{
                //if no water flow is found, then the river ends here
                if (river.getRiverSize() > 0){
                    createEndorheicLake(river, previousRiverVertex);
                }
                else{
                    validRiver = false;
                }
                break;
            }
        }
    }

    public List<River> getRivers(){
        return this.rivers;
    }

    private void createEndorheicLake(River river, TileVertex previousRiverVertex){
        //Find a suitable tile to be a endorheic lake 

        Boolean besideOcean; 
        validRiver = false;
        for (Tile tile: tiles){
            if (tile.isTileVerticesListContains(previousRiverVertex) && !tile.isTileSegmentListContains(river.getRiverlastSegment())){
                //ensure no neighbouring tiles are type oceans
                besideOcean = false;
                for (Tile neighbouringTile: tile.getNeighbouringTile()){
                    if (neighbouringTile.isTileOcean()){
                        besideOcean = true;
                        break;
                    }
                }

                if (!besideOcean){
                    tile.setTileType(TileTypes.ENDORHEICLAKE);

                    List<Tile> endorheic_list=new ArrayList<>();
                    endorheic_list.add(tile);

                    Lake endorheicLake=new Lake(endorheic_list);
                    endorheicLake_list.add(endorheicLake);
                    tiles.removeAll(endorheic_list);
                    validRiver = true;
                    break;
                }
            }
        }
    }

    public List<Lake> getEndorheicLakes()
    {
        return endorheicLake_list;
    }

    public List<Tile> getRemainingTiles(){
        return tiles;
    }


    
}
