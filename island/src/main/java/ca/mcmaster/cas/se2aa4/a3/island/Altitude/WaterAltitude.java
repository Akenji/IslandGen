package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.List;

public interface WaterAltitude {

    //More functions to come in future.
    public AltitudeFunction getFunction(List<Tile> tiles);

    public void findElevation();

}
