package ca.mcmaster.cas.se2aa4.a3.island.Modes;
import java.io.IOException;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.util.List;

public class Sandbox extends Mode{
    
    public Sandbox(String inputMesh, String outputMesh) throws IOException{
        super(inputMesh, outputMesh, ShapeType.CIRCLE, null, null, null, null);

         //extract all the info from the input mesh
         extractInformation();
    }

    public void generate(){
        //generate a map following the sandbox requirements

        //Outer Circle
        Circle outerCircle = new Circle(width, height, tiles);
        List<Tile> oceanTiles = outerCircle.getOutOfRangeTiles();
        List<Tile> undecidedTiles = outerCircle.getInRangeTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
        }

        //Inner Circle
        Circle innerCircle = new Circle(outerCircle.getRadius(), outerCircle.getRadius(), outerCircle.getCenterX(), outerCircle.getCenterY(), undecidedTiles);
        List<Tile> landTiles = innerCircle.getOutOfRangeTiles();
        List<Tile> lagoonTiles = innerCircle.getInRangeTiles();

        //Set lagoon tiles
        for(Tile tile: lagoonTiles){
            tile.setTileType(TileTypes.LAGOON);
        }

        //Set the unMarked Tiles color
        for(Tile tile: landTiles){
            tile.setTileType(TileTypes.GRASSLAND);

        }


        //Set beach tiles 
        determineBeachLand(landTiles);
    }

    private void determineBeachLand(List<Tile> landTiles){
        //Any Tile that are neighbours to a water tile is a beach tile 
        for(Tile tile: landTiles){
            for(Tile neighbouringTile: tile.getNeighbouringTile()){
                if (neighbouringTile.isTileWater()){
                    tile.setTileType(TileTypes.BEACH);
                    break;
                }
            }
        }
    }

}
