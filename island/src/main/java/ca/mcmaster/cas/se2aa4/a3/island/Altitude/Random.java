package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

public class Random implements LandAltitude{

    public AltitudeFunction function= (x,y)->{return IslandCommandLineReader.randomGenerator.getNextDouble(0.0,1000);};

    public AltitudeFunction getFunction(){
        return this.function;
    }
}
