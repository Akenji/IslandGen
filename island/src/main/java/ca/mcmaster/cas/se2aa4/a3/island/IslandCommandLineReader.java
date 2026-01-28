package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Heatmaps;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.ModeType;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Regular;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Sandbox;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

import org.apache.commons.cli.*;

import java.io.IOException;


public class IslandCommandLineReader implements CommandLineReader {
    private String inputMeshFile;
    private String outputMeshFile;
    private String mode;
    private String shape;
    private String seed;
    private String river;
    private String maxLakes;
    private String aquifer;
    private String cities;
    private String elevation;
    private String biome;

    private String soil;
    private ModeType mapMode = null;
    private AltitudeType altitude = null;
    private ShapeType shapeToUse = null;
    private BiomeTypes generalBiome = null;

    private SoilTypes generalSoil;
    private int maxNumLakes;
    private int maxNumRivers;
    private int numAquifers;
    private int numCities;
    private Options options;

    public static RandomGenerator randomGenerator=new RandomGenerator();

    public IslandCommandLineReader(String[] args) throws IOException, ParseException {
        super();
        options = new Options();
        createOptions();
        checkOptions(args);

        //Set the random generator class 
        if (seed == null){
            //Create new seed
            randomGenerator = new RandomGenerator();
        }
        else{
            //use the inputted seed
            randomGenerator = new RandomGenerator(Long.parseLong(seed));
        }
    }
    public void createOptions(){
        //Creates all the options for the command line
        options.addOption(new Option("i", "inputMesh", true, "Input Mesh (String)"));
        options.addOption(new Option("o", "outputMesh", true, "Output Mesh (String)"));
        options.addOption(new Option("m", "mode", true, "Map Mode {sandbox, regular, heatmap}"));
        options.addOption(new Option("sh", "shape", true, "Island Shape {circle, rectangle, oval, random, irregular}"));
        options.addOption(new Option("a", "altitude", true, "Island Elevation {volcanic, water, cliff, hills, flat, random}"));
        options.addOption(new Option("se", "seed", true, "Map seed (Long)"));
        options.addOption(new Option("b", "biomes", true, "Biome type {arctic, temperate, tropical, dessert}"));
        options.addOption(new Option("r", "rivers", true, "Maximum number of rivers to generate (Positive Integer)"));
        options.addOption(new Option("l", "lakes", true, "Maximum number of lakes (Positive Integer)"));
        options.addOption(new Option("s", "soil", true, "Enter the soil profile {wet, humid, dry}"));
        options.addOption(new Option("aq", "aquifers", true, "Number of aquifers (Positive Integer)"));
        options.addOption(new Option("c", "cities", true, "Number of cities (Positive Integer)"));
        options.addOption(new Option("h", "help", false, "Help"));

    }

    public void checkOptions(String[] args) throws ParseException, IOException {
        //check all the options from the command line to figure what information to extra or display
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //extract the values from each option
        inputMeshFile = cmd.getOptionValue("inputMesh");
        outputMeshFile = cmd.getOptionValue("outputMesh");
        mode = cmd.getOptionValue("mode");
        shape = cmd.getOptionValue("shape");
        elevation = cmd.getOptionValue("altitude");
        seed = cmd.getOptionValue("seed");
        biome = cmd.getOptionValue("biomes");
        river = cmd.getOptionValue("rivers");
        maxLakes = cmd.getOptionValue("lakes");
        soil=cmd.getOptionValue("soil");
        aquifer = cmd.getOptionValue("aquifers");
        cities = cmd.getOptionValue("cities");

        //Help option
        if (cmd.hasOption("help")) {
            System.out.println("Create Sandbox Island: java -jar island.jar -inputMesh -outputMesh --mode sandbox");
            System.out.println("Create Regular Island: java -jar island.jar -inputMesh -outputMesh --mode regular -shape -altitude -biomes [-lakes] [-rivers] -soil [-aquifers] [-seed]");
            System.out.println("Create Heatmap: java -jar island.jar -inputMesh -outputMesh --mode heatmap -shape -altitude -biomes [-lakes] [-rivers] -soil [-aquifers] [-seed]");
            System.out.println("Options in square brackets are optional and the rest of the parameters are required to be defines");
            System.out.println("");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            System.exit(0);
        }

        //Set the mode to run
        for (ModeType m : ModeType.values()) {
            String modeString = m.toString();
            if (modeString.equals(mode)) {
                mapMode = m;
                break;
            }
        }

        //Get the shape type
        for (ShapeType s : ShapeType.values()) {
            String shapeString = s.toString();
            if (shapeString.equals(shape)) {
                shapeToUse = s;
                break;
            }
        }

        //Get the altitude type
        for (AltitudeType a : AltitudeType.values()) {
            String altitudeString = a.toString();
            if (altitudeString.equals(elevation)) {
                altitude = a;
                break;
            }
        }

        for (BiomeTypes b : BiomeTypes.values()) {
            String biomeString = b.toString();
            if (biomeString.equals(biome)) {
                generalBiome = b;
                break;
            }
        }

        for (SoilTypes s : SoilTypes.values()) {
            String soilString = s.toString();
            if (soilString.equals(soil)) {
                generalSoil = s;
                break;
            }
        }
        //Set the maximum number of lakes
        if (cmd.hasOption("lakes")) {
            maxNumLakes = Integer.parseInt(maxLakes);
        } else {
            maxNumLakes = 0;
        }

        //Set the maximum number of rivers
        if (cmd.hasOption("rivers")) {
            maxNumRivers = Integer.parseInt(river);
        } else {
            maxNumRivers = 0;
        }

        //Set the number of aquifers
        if (cmd.hasOption("aquifers")) {
            numAquifers = Integer.parseInt(aquifer);
        } else {
            numAquifers = 0;
        }

        if (cmd.hasOption("cities")) {
            numCities = Integer.parseInt(cities);
        } else {
            numCities = 0;
        }

        //Output the user input

        if (mapMode.equals(ModeType.SANDBOX)){
            System.out.println("Mode: " + mode);
        }
        else{
            System.out.println("Input Mesh Path: " + inputMeshFile + "Output Mesh Path: " + outputMeshFile + "Mode: " + mode + "\nShape: " + shape + "\nAltitude: " + elevation + "\nBiomes: " + biome + "\nLakes: " + maxNumLakes + "\nRivers: " + maxNumRivers + "\nSoil Profile: " + soil + "\nAquifiers: " + numAquifers + "\nCities: " + numCities);
        }
    }
    
    public String getOutputMeshFile(){
        return outputMeshFile;
    }

    protected boolean isSandBoxMode(){
        if (mapMode.equals(ModeType.SANDBOX)){
            return true;
        }
        return false;
    }
    protected boolean isRegularMode(){
        if (mapMode.equals(ModeType.REGULAR)){
            return true;
        }
        return false;
    }

    protected boolean isHeatmapMode(){
        if (mapMode.equals(ModeType.HEATMAP)) {
            return true;
        }
        return false;
    }
    public Mesh generateFromInputs() throws IOException{
        RunMode runMode = new RunMode();
        return runMode.getMesh();
    }

    private class RunMode{
        Mesh mesh = null;
        public RunMode() throws IOException{   
            if (isSandBoxMode()){
                Sandbox sandbox = new Sandbox(inputMeshFile, outputMeshFile);
                sandbox.generate();
                mesh = sandbox.getMesh();
            }
            else if (isRegularMode()){
                Regular regular = new Regular(inputMeshFile, outputMeshFile, shapeToUse, altitude, generalBiome, maxNumLakes, maxNumRivers, generalSoil, numAquifers, numCities);
                regular.generate();
                mesh = regular.getMesh();
            }else if (isHeatmapMode()){
                Heatmaps heatmap=new Heatmaps(inputMeshFile, outputMeshFile, shapeToUse, altitude, generalBiome,maxNumLakes, maxNumRivers,generalSoil, numAquifers, numCities);
                mesh=heatmap.getMesh();
            }
            else{
                throw new IOException("Invalid mode was entered");
            }
        }
        public Mesh getMesh(){
            return mesh;
        }
    }
}
