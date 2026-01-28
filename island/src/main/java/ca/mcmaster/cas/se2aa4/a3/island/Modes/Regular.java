package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.io.IOException;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.Networks.StarNetwork;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.BeachGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.CityGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.Land;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.AquiferGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.Ocean;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.RiverGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.GeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;
import org.locationtech.jts.awt.PointShapeFactory;

public class Regular extends Mode {
    private int maxNumLakes;
    private int maxNumRivers;
    private int numAquifers;
    private int numCities;
    

    public Regular(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxNumRivers, SoilTypes soil, int numAquifers, int numCities) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, Integer.toString(maxLakes),soil);
        
        extractInformation();
        this.maxNumLakes = maxLakes;
        this.maxNumRivers = maxNumRivers;
        this.numAquifers = numAquifers;
        this.numCities = numCities;
    }

    public void generate(){
        //generate the map based on the user inputs

        //Segment the island into ocean and undecided tiles
        Shape islandShape = shape.getShape(width, height, tiles);
        List<Tile> oceanTiles = islandShape.getOutOfRangeTiles();
        List<Tile> undecidedTiles = islandShape.getInRangeTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
            Ocean ocean_tile=new Ocean(tile);
            allWater.add(ocean_tile);
        }

        //Generate the lakes
        LakeGenerator lakeGenerator = new LakeGenerator(maxNumLakes, undecidedTiles);
        lakeGenerator.generate();
        undecidedTiles = lakeGenerator.getRemainingTiles();

        allWater.addAll(lakeGenerator.getLakes());

        //Set the altitude
        altitude_gen.setAll(altitude, undecidedTiles, oceanTiles, lakeGenerator.getLakes());

        //Generate the rivers
        RiverGenerator riverGenerator = new RiverGenerator(undecidedTiles, maxNumRivers);
        riverGenerator.generate();
        allWater.addAll(riverGenerator.getRivers());
        allWater.addAll(riverGenerator.getEndorheicLakes());

        altitude_gen.setLakes(riverGenerator.getEndorheicLakes());
        //Remove endorheic lake tiles from undecided tiles
        undecidedTiles=riverGenerator.getRemainingTiles();

        //Create beaches
        BeachGenerator beachGenerator = new BeachGenerator(biome.doesContaiBeaches(), undecidedTiles);
        beachGenerator.generate();
        undecidedTiles = beachGenerator.getRemainingTiles();

        //Generate the general biome of the map
        GeneralBiome generalBiome = biome.getGeneralBiome();

        //Set temperature to all land and water tiles
        temperature_gen.setTemperature(tiles, generalBiome.getBaseTemperature(), altitude_gen.getMinElevation());
        
        //Create Land tiles
        for(Tile tile: undecidedTiles){
            Land landtile=new Land(tile);
            allLand.add(landtile);
        }

        AquiferGenerator aquiferGenerator = new AquiferGenerator(tiles, numAquifers);
        aquiferGenerator.createAquifers();
        allWater.addAll(aquiferGenerator.getAquifers());

        //Set humidity to all land tiles
        humidity.SetHumidity(allLand,allWater);

        //Set biomes to all land tiles based on their average temperature and humidity level
        TileTypes landBiome;
        generalBiome.createWhittakerDiagram(humidity.getHumidityRange(), humidity.getMinHumidity(), temperature_gen.getTemperatureRange(), temperature_gen.getLowestTemp());
        for (Land landTile : allLand){
            landBiome = generalBiome.getTileBiome(landTile.getHumidity(), landTile.getAverageTemperature());
            landTile.setTileType(landBiome);
        }

        CityGenerator cityGenerator = new CityGenerator(allLand, numCities);
        cityGenerator.generate();
        StarNetwork network = new StarNetwork(cityGenerator.getGraph(), cityGenerator.getCities(), allLand);
        network.createNetwork();

        //Set humidity contrast colours to all land tiles
        humidity.setHumidityColors(allLand);
    }

}
