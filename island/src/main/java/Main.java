import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import org.apache.commons.cli.ParseException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        IslandCommandLineReader commandReader = new IslandCommandLineReader(args);
        MeshFactory factory = new MeshFactory();
        factory.write(commandReader.generateFromInputs(), commandReader.getOutputMeshFile());
    }
}
