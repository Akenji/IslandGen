package ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public abstract class BodiesWater {
    Double humidity_level;

    public abstract Double getHumidityLevel();

    public abstract List<TileVertex> getMidPoints();
}
