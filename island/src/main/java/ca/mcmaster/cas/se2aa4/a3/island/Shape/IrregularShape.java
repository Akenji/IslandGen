package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import java.util.ArrayList;
import java.util.List;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.pipeline.JNoise;
import ca.mcmaster.cas.se2aa4.a3.island.IslandCommandLineReader;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import de.articdive.jnoise.generators.noise_parameters.interpolation.*;

public class IrregularShape extends Shape{
    private List<Tile> undeterminedTiles;
    final JNoise perlin;
    private double width;
    private double height;
    
    public IrregularShape (double width, double height, List<Tile> tiles){
        super();
        this.tiles = tiles;
        this.width = width;
        this.height = height;

        generateBaseIsland();
        perlin = JNoise.newBuilder().perlin(IslandCommandLineReader.randomGenerator.getSeed(), Interpolation.COSINE, FadeFunction.SMOOTHSTEP).build();
        markTiles();
    }

    public List<Tile> getOutOfRangeTiles(){
        return outOfRangeTiles;
    }
    public List<Tile> getInRangeTiles(){
        return inRangeTiles;
    }

    private void generateBaseIsland(){
        //generate the base island for the irregular island to step out of

        int shapeVal = (int)IslandCommandLineReader.randomGenerator.getNextDouble(0,2.5);
        ShapeType shapeType = ShapeType.values()[shapeVal];
        
        if (shapeType.equals(ShapeType.CIRCLE)){
            Circle island = new Circle(width, height, tiles);
            outOfRangeTiles = island.getOutOfRangeTiles();
            undeterminedTiles = island.getInRangeTiles();

            Circle base = new Circle(island.getRadius(), island.getRadius(), island.getCenterX(), island.getCenterY(), undeterminedTiles);
            undeterminedTiles = base.getOutOfRangeTiles();
            inRangeTiles = base.getInRangeTiles();
        }
        else if (shapeType.equals(ShapeType.RECTANGLE)){
            Rectangle island = new Rectangle(width, height, tiles);
            outOfRangeTiles = island.getOutOfRangeTiles();
            undeterminedTiles = island.getInRangeTiles();

            Rectangle base = new Rectangle(island.getMaxSize(), island.getMaxSize(), island.getAngleRad(), undeterminedTiles);
            undeterminedTiles = base.getOutOfRangeTiles();
            inRangeTiles = base.getInRangeTiles();
        }
        else if (shapeType.equals(ShapeType.OVAL)){
            Oval island = new Oval(width, height, tiles);
            outOfRangeTiles = island.getOutOfRangeTiles();
            undeterminedTiles = island.getInRangeTiles();

            Oval base = new Oval(island.getLength(), island.getLength(), island.getAngleRad() ,undeterminedTiles);
            undeterminedTiles = base.getOutOfRangeTiles();
            inRangeTiles = base.getInRangeTiles();
        }
    }

    public void markTiles(){
        //extend the base randomly adding neighbouring polygons to the list using perlin noise

        double noise;
        List<Tile> waterTiles = new ArrayList<>();
        List<Tile> waterBody = new ArrayList<>();
        List<Tile> queue = new ArrayList<>();
        Tile parentTile;
        Boolean isOutOfRange;
        int counter;

        //Apply perlin noise to extend the island randomly from the base
        for (Tile tile: undeterminedTiles){
            noise = perlin.evaluateNoise(tile.getCentroidX()/(width*0.2), tile.getCentroidY()/(height*0.2));
            if (noise > 0){
                inRangeTiles.add(tile);
            }
            else{
                waterTiles.add(tile);
            }
        }

        //Remove any lakes in the island
        while (!waterTiles.isEmpty()){
            waterBody.add(waterTiles.remove(0));
            queue.add(waterBody.get(0));
            isOutOfRange = false;
            counter = 0;

            while (counter < queue.size()){
                parentTile = queue.get(counter);

                //Visit each neighbouring tile of parentTile from the queue and check if any are connected to a tile that is out of range
                for(Tile tile: parentTile.getNeighbouringTile()){
                    if (outOfRangeTiles.contains(tile)){
                        isOutOfRange = true;
                    }
                    else{
                        //Add tile to queue if it is range is unknown and already not in queue
                        if (!inRangeTiles.contains(tile) && !queue.contains(tile)){
                            waterBody.add(tile);
                            waterTiles.remove(tile);
                            queue.add(tile);
                        }
                    }
                    counter++;
                }
            }
            
            if (isOutOfRange){
                //this list of tiles are ocean tiles
                outOfRangeTiles.addAll(waterBody);
            }
            else{
                //this list of tiles are a lake and should be converted as to land
                inRangeTiles.addAll(waterBody);
            }

            waterBody = new ArrayList<>();
            queue = new ArrayList<>();
       }

    }
}
