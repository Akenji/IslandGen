package ca.mcmaster.cas.se2aa4.a2.visualizer;
import java.io.IOException;
import org.apache.commons.cli.ParseException;
import ca.mcmaster.cas.se2aa4.a3.tools.CommandLineReader;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.DefaultParser;

public class VisualizerCommandLineReader implements CommandLineReader{
    private Boolean debug = false;

    private Boolean debugElevation=false;
    private Options options;
    public VisualizerCommandLineReader(String[] args)throws IOException, ParseException{
        super();
        options = new Options();
        createOptions();
        checkOptions(args);
    }

    public  void createOptions(){
        options.addOption(new Option("X", "debug", false, "Debug mode"));
        options.addOption(new Option("XE", "debugElevation", false, "Debug Elevation mode"));

    }

    public  void checkOptions(String[] args)throws ParseException, IOException{
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("debug")) {
            debug = true;
        }else if (cmd.hasOption("debugElevation")){
            debugElevation=true;
        }
    }

    public boolean isDebug(){
       return debug; 
    }

    public boolean isElevation(){
        return debugElevation;
    }

    
}
