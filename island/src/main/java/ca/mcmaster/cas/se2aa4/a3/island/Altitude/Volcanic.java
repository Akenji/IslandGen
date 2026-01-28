package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;


public class Volcanic implements LandAltitude{


    Double spread;
    Double height;

    public AltitudeFunction function= (x,y)->{return height*Math.exp(-1*((Math.pow((x-Mode.getWidth()/2),2)+Math.pow((y-Mode.getHeight()/2),2))/(spread)));};

    public AltitudeFunction getFunction(){
        spread=IslandCommandLineReader.randomGenerator.getNextDouble(10000.0,20000.0);
        height=IslandCommandLineReader.randomGenerator.getNextDouble(4000.0,4500.0);

        return this.function;
    }

}
