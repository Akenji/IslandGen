package ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class Land {

    Tile landtile;
    SoilTypes soilType;
    Double humidity;
    Double soilCoefficient;

    LandComposition composition;
    double averageTemperature;


    public Land(Tile landtile){
        this.landtile=landtile;
        this.soilType= Mode.getSoil();
        this.humidity=0.0;
        this.soilCoefficient=soilType.getSoilCoefficient();
        this.averageTemperature=landtile.getAverageTemperature();
    }

    public void setHumidity(Double humidity){
        this.humidity=humidity;
    }

    public Double getHumidity(){
        return this.humidity;
    }

    public Double getCoefficient(){
        return this.soilCoefficient;
    }

    public double getAverageTemperature(){
        return this.averageTemperature;
    }

    public Tile getTile(){
        return this.landtile;
    }

    public void setTileType(TileTypes tileType){
        this.landtile.setTileType(tileType);
    }
}
