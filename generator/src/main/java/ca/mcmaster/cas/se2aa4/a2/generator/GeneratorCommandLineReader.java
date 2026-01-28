package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.MeshType;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.io.IOException;
import org.apache.commons.cli.*;


public class GeneratorCommandLineReader implements CommandLineReader{
    //default values
    int numberPolygons = 0;
    int canvasWidth = 500;
    int canvasHeight = 500;
    int relationLevel = 0;
    int gridSpacing = 20;
    MeshType meshType;
    String fileName;
    private Options options;
    
    public GeneratorCommandLineReader(String[] args) throws IOException, ParseException{
        super();
        options = new Options();
        createOptions();
        checkOptions(args);
        fileName = args[0];
    }
    public void createOptions(){
        //Creates all the options for the command line
        options.addOption(new Option("g", "grid", false, "Generate Grid Mesh"));
        options.addOption(new Option("i", "irregular", false, "Generate Irregular Mesh"));
        options.addOption(new Option("np", "numPoly", true, "Number of Polygons"));
        options.addOption(new Option("wth", "width", true, "Width of Canvas"));
        options.addOption(new Option("hth", "height", true, "Height of Canvas"));
        options.addOption(new Option("rl", "relation", true, "Relaxation level for Llyod relaxation"));
        options.addOption(new Option("h", "help", false, "Available Options and Command Line Arguments"));
        options.addOption(new Option("s", "spacing", true, "Grid spacing amount"));
    }

    public void checkOptions(String[] args) throws ParseException, IOException{
        //check all the options from the command line to figure what information to extra or display
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        //extract the values from each option
        String numPoly = cmd.getOptionValue("numPoly");
        String width = cmd.getOptionValue("width");
        String height = cmd.getOptionValue("height");
        String relation = cmd.getOptionValue("relation");
        String spacing = cmd.getOptionValue("spacing");
        

        //Help option
        if(cmd.hasOption("help")) {
            System.out.println("Grid-Based Mesh: java -jar generator.jar sample.mesh -grid [-width] [-height] [-spacing]");
            System.out.println("Irregular Mesh: java -jar generator.jar sample.mesh -irregular -numPoly [-width] [-height] [-relation]");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("help", options);
            System.exit(0);
        }
        
        //Checks if valid input for generating grid or irregular mesh
        if(cmd.hasOption("grid") && cmd.hasOption("irregular")) {
            throw new IOException("Can not generate both grid and irregular mesh");
        }
        else if (cmd.hasOption("grid")) {
            meshType = MeshType.GRID;
        }
        else if (cmd.hasOption("irregular")) {
            meshType = MeshType.IRREGULAR;
        }
        else{
            throw new IOException("Need to generate either grid or irregular mesh");
        }

        //Extract values for attributes
        if(numPoly != null){
            numberPolygons = Integer.parseInt(numPoly);
            if (numberPolygons < 10){
                System.out.println("There must be atleast 10 polygons generated");
                numberPolygons = 10;
            }
        }
        if(width != null){
            canvasWidth = Integer.parseInt(width);
        }
        if(height != null){
            canvasHeight = Integer.parseInt(height);
        }
        if(relation != null){
            relationLevel = Integer.parseInt(relation);
        }
        if(spacing != null){
            gridSpacing = Integer.parseInt(spacing);
            if (gridSpacing < 10){
                gridSpacing = 20;
            }
        }
    }
    public Mesh createMesh() throws IOException
    {
        //Generate the mesh based on the inputs from the command line
        Generator generator = new Generator();
        return generator.generate(numberPolygons, canvasWidth, canvasHeight, meshType, relationLevel, gridSpacing);
    }
    public String getFileName(){
        return fileName;
    }
}
