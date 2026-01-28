package ca.mcmaster.cas.se2aa4.a3.island.BiomeTesting;

import org.junit.jupiter.api.Test;
import java.awt.geom.Area;
import java.awt.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.ArcticGeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.DesertGeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.TemperateForestGeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.TropicalRainforestGeneralBiome;

import static org.junit.jupiter.api.Assertions.*;

public class WhittakerDiagramTest extends SetUpTest {
    @Test
    public void ArticWhittakerDiagramArea(){
        Area total = new Area();
        ArcticGeneralBiome biome = new ArcticGeneralBiome(270);
        biome.createWhittakerDiagram(width, 0, height, 0);
        for (Shape shape: biome.whittakerDiagram.getWhittakerDiagram().keySet()){
            total.add(new Area(shape));
        }

        assertTrue(total.contains(0, 0, 100, 100));
    }

    @Test
    public void TropicalWhittakerDiagramArea(){
        Area total = new Area();
        TropicalRainforestGeneralBiome biome = new TropicalRainforestGeneralBiome(290);
        biome.createWhittakerDiagram(width, 0, height, 0);
        for (Shape shape: biome.whittakerDiagram.getWhittakerDiagram().keySet()){
            total.add(new Area(shape));
        }

        assertTrue(total.contains(0, 0, 100, 100));
    }

    @Test
    public void TemperateWhittakerDiagramArea(){
        Area total = new Area();
        TemperateForestGeneralBiome biome = new TemperateForestGeneralBiome(280);
        biome.createWhittakerDiagram(width, 0, height, 0);
        for (Shape shape: biome.whittakerDiagram.getWhittakerDiagram().keySet()){
            total.add(new Area(shape));
        }

        assertTrue(total.contains(0, 0, 100, 100));
    }

    @Test
    public void DesertWhittakerDiagramArea(){
        Area total = new Area();
        DesertGeneralBiome biome = new DesertGeneralBiome(280);
        biome.createWhittakerDiagram(width, 0, height, 0);
        for (Shape shape: biome.whittakerDiagram.getWhittakerDiagram().keySet()){
            total.add(new Area(shape));
        }

        assertTrue(total.contains(0, 0, 100, 100));
    }

}
