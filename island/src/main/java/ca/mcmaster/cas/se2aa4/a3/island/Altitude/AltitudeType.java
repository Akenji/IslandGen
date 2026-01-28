package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;

import java.util.List;

public enum AltitudeType {
    VOLCANIC, WATER, CLIFF,HILLS, FLAT, RANDOM;


    public String toString(){
        switch(this){
            case VOLCANIC :
                return "volcanic";
            case WATER:
                return "water";
            case CLIFF:
                return "cliff";
            case HILLS:
                return "hills";
            case FLAT:
                return "flat";
            case RANDOM:
                return "random";
        }
        return null;
    }
    public AltitudeFunction getAltitude(List<Tile> tiles){
        switch(this){
            case VOLCANIC :
                return new Volcanic().getFunction();
            case WATER:
                return new Water().getFunction(tiles);
            case CLIFF:
                return new Cliff().getFunction();
            case HILLS:
                return new Hills().getFunction();
            case FLAT:
                return new Flat().getFunction();
            case RANDOM:
                return new Random().getFunction();
        }
        return null;
    }
}
