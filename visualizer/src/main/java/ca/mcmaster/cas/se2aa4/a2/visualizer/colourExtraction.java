package ca.mcmaster.cas.se2aa4.a2.visualizer;
import java.awt.Color;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public interface  colourExtraction {
    Color getColor();
    Color extractColor(List<Property> properties);
}
