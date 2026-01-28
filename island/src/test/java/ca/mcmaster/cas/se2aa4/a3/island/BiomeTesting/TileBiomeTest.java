package ca.mcmaster.cas.se2aa4.a3.island.BiomeTesting;

import org.junit.jupiter.api.Test;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.ArcticGeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.GeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import static org.junit.jupiter.api.Assertions.*;

public class TileBiomeTest extends SetUpTest{

    @Test
    public void biomeAssignmentTest(){
        GeneralBiome generalBiome = new ArcticGeneralBiome(270);
        generalBiome.createWhittakerDiagram(width, 0, height, 0);
        TileTypes biome;
        
        for (Tile tile : tiles){
            biome = generalBiome.getTileBiome(tile.getCentroidX(), tile.getCentroidY());
            assertTrue(!biome.equals(TileTypes.UNDETERMINEDLAND));
        }
    }
    @Test
    public void biomeAssignmentOffsetTest(){
        GeneralBiome generalBiome = new ArcticGeneralBiome(270);
        int offset = 100;
        generalBiome.createWhittakerDiagram(width + offset, offset, height + offset, offset);
        TileTypes biome;
        
        for (Tile tile : tiles){
            biome = generalBiome.getTileBiome(tile.getCentroidX(), tile.getCentroidY());
            assertTrue(!biome.equals(TileTypes.UNDETERMINEDLAND));
        }
    }
    
}
