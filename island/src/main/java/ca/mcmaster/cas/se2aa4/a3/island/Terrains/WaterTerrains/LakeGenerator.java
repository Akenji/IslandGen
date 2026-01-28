package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Generator;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class LakeGenerator implements Generator{
    private int maxNumLakes;
    private List<Tile> undecidedTiles;
    private List<Tile> potentialLakeTiles;
    private List<Lake> lakes;

    public LakeGenerator(int maxNumLakes, List<Tile> undecidedTiles){
        this.maxNumLakes = maxNumLakes;
        this.undecidedTiles = undecidedTiles;
        this.potentialLakeTiles = new ArrayList<>(undecidedTiles);
        this.lakes = new ArrayList<>();
    }

    public void generate(){
        //Generate the Lake Tiles

        int numLakes = IslandCommandLineReader.randomGenerator.getNextInteger(0, maxNumLakes);
        determineLakeTiles();
        int maxLakeSize = (int) Math.floor(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));
        Lake lake;

        for(int i = 0; i < numLakes; i++){
            lake=createLake(potentialLakeTiles, maxLakeSize);
            lakes.add(lake);
            potentialLakeTiles.removeAll(lake.getLakeTiles());
            undecidedTiles.removeAll(lake.getLakeTiles());
        }
    }


    private Lake createLake(List<Tile> undecidedTiles, int maxLakeSize){

        int lakeSize = IslandCommandLineReader.randomGenerator.getNextInteger(1,maxLakeSize+1); // lake must be made up of at least 2 tiles
        List<Tile> lakeTiles=new ArrayList<>();

        Tile currentTile = undecidedTiles.remove(IslandCommandLineReader.randomGenerator.getNextInteger(0,undecidedTiles.size()));
        currentTile.setTileType(TileTypes.LAKE);
        lakeTiles.add(currentTile);
        int tilesAdded = 1;
        List<Tile> neighbouringTiles;

        while (tilesAdded < lakeSize) {
            neighbouringTiles = currentTile.getNeighbouringTile();

            for (Tile tile : neighbouringTiles) {
                if (tilesAdded < lakeSize && undecidedTiles.contains(tile)) {
                    tilesAdded++;
                    tile.setTileType(TileTypes.LAKE);
                    lakeTiles.add(tile);
                    undecidedTiles.remove(tile);
                }
            }

            currentTile = neighbouringTiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0,currentTile.numNeighbouringTiles()));
        }

        return new Lake(lakeTiles);
    }

    private void determineLakeTiles(){
        List<Tile> beachTiles = new ArrayList<>();

        for(Tile tile: undecidedTiles){
            for(Tile neighbouringTile: tile.getNeighbouringTile()){
                if (neighbouringTile.isTileWater()){
                    beachTiles.add(tile);
                    break;
                }
            }
        }

        potentialLakeTiles.removeAll(beachTiles);
    }

    public List<Lake> getLakes(){
        return this.lakes;
    }

    public List<Tile> getRemainingTiles(){
        return undecidedTiles;
    }
    
}
