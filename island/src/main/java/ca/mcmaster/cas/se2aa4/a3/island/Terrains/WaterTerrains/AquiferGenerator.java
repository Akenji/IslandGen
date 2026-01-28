package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;

import java.util.ArrayList;
import java.util.List;

public class AquiferGenerator {
    private int numAquifers;
    List<Tile> tiles;
    List<Aquifer> aquifers;

    public AquiferGenerator(List<Tile> tiles, int numAquifers){
        this.tiles = tiles;
        this.numAquifers = numAquifers;
        this.aquifers = new ArrayList<>();
    }

    public void createAquifers(){
        Tile currentTile = null;
        Aquifer aquifer;

        for (int i = 0; i < numAquifers; i++){
            currentTile = tiles.get(IslandCommandLineReader.randomGenerator.getNextInteger(0,tiles.size()));
            aquifer = new Aquifer(currentTile);
            aquifers.add(aquifer);
        }

    }

    public List<Aquifer> getAquifers(){return this.aquifers;}
}
