# Island City Pathfinder
Irregular Mesh Example

<img src = "images/irregular.png" width ="600">

Tropical Island Example

<img src ="images/tropical.png" width ="400">

This is a procedural island map generator. It creates realistic, customizable island landscapes for potential use in simulations, visualizations, or games.

Core Pipeline (3 Main Components):
### 1. Mesh Generator

Creates the geometric foundation - a 2D mesh of interconnected polygons
Two modes:
Grid-based: Regular square/rectangular tiles
Irregular: Organic, natural-looking polygons using Voronoi diagrams and Delaunay triangulation
Each polygon has vertices, segments (edges), centroids, and neighbor relationships
Output: Binary .mesh file using Protocol Buffers
### 2. Island Generator

Takes a mesh and transforms it into a rich island map with geographic features
Three modes:
Sandbox: Basic testing/experimentation mode
Regular: Full island generation with:
Shapes: Circle, rectangle, oval, irregular, or random
Altitude profiles: Volcanic peaks, cliffs, rolling hills, flat terrain
Biomes: Arctic (tundra), tropical (jungle), temperate (forests), desert
Water features: Lakes, rivers, aquifers
Soil profiles: Dry, humid, wet (affects moisture/vegetation)
Heatmap: Visualizes environmental data (altitude, moisture levels)
Uses Whittaker diagrams (biome classification based on temperature/moisture) to assign realistic ecosystems
Supports seeded random generation for reproducibility
### 3. Visualizer

Converts mesh files into SVG images you can view in a browser
Regular mode: Shows the island with colors representing terrain/biomes
Debug mode (-X flag): Shows technical structure - vertices (black), centroids (red), neighbor connections (grey)

## Pathfinder
it’s the routing engine to traverse the generated island realistically, with costs you define.

Purpose: Implements Dijkstra’s shortest-path algorithm to compute optimal routes between nodes.
What it’s for here: Routing on the island graph (e.g., between polygon centroids or city locations), where edge weights can encode terrain costs (e.g., water = high cost, roads = low cost), distance, or other penalties.

#### Typical uses on this project:
Roads: Connect cities/Points of intersection with least-cost routes across the island.
Analysis: Compute service reachability, minimum spanning-like corridors (iterating shortest paths).

# Here's the simplest way to run and see results:

## step 1: Install the different tooling on your computer:

```
mvn install
```
After installation, you'll find an application named `generator.jar` in the `generator` directory, another file name `island.jar` in the `island` directory and a file named `visualizer.jar` in the `visualizer`. 

## step 2: Generate a mesh (the base geometry)
```
cd generator
java -jar generator.jar sample.mesh -grid [-width] [-height] [-spacing]
```
`width` and `height` default is 500px while `spacing` default is 20 and are optional to change in the command line. Note that the minimum spacing is 10. 

Example: `java -jar generator.jar sample.mesh -grid -width 350 -height 350`

#### Irregular Mesh Generation
```
cd generator 
java -jar generator.jar sample.mesh -irregular -numPoly [-width] [-height] [-relation]
```
`numPoly` is the number of polygons to generate on the mesh.\
`width` and `height` default is 500px and are optional to change in the command line. \
`relation` default is 1 and value can be changed for any values greater than 1 to smooth the mesh.

Example: `java -jar generator.jar sample.mesh -irregular -numPoly 500 -width 600 -height 600 -relation 3`


## step 3: Create an island (add terrain, water, biomes)
Generates a map based on input mesh and command line arguments. 

#### Sandbox 
To run sandbox mode of the island generator, go to the `island` directory, and use `java -jar` to run the product. Use the mesh created by the generator as input mesh and enter .mesh file name to save the island generated map.  

```
cd island
java -jar island.jar -input (input.mesh) -output (output.mesh) --mode sandbox
```

Example: `java -jar island.jar -i ../generator/sample.mesh -o sandbox.mesh --mode sandbox`

#### Regular
To run the regular mode of the island generator, go to the `island` directory, and use `java -jar` to run the product. Use the mesh created by the generator as input mesh and enter .mesh file name to save the island generated map.  

```
cd island
java -jar island.jar -input (input.mesh) -output (output.mesh) --mode regular -shape {circle, rectangle, oval, irregular, random} -altitude {volcanic, cliff, hills, flat, random} -biomes {arctic, tropical, temperate, desert} [-lakes] [-rivers] -soil {dry, humid, wet} [-aquifers] [-seed]
```

Example: `java -jar island.jar -i ../generator/regular.mesh -o desert.mesh --mode regular --shape irregular --altitude hills --biomes desert --lakes 2 --rivers 2 -soil dry --aquifers 2 -seed -2116125981790618405`

`shape`: General shape of the island: {circle, rectangle, oval, irregular, random}.\
`altitude` : The altitude behaviour of the island: {volcanic, cliff, hills, flat, random}.\
`biomes`: General biome to use for the whole map: {arctic, tropical, temperate, desert}.\
`lakes`: Maximum number of lakes in the island: (positive integer).\
`rivers`: Maximum number of rivers in the island: (positive integer).\
`soil`: The soil profile to apply to the whole island: {dry, humid, wet}.\
`aquifers`: Number of aquifers to generate on the island: (positive integer).\
`seed`: Seed to use for all random variables: (long type)

## Step 4: Visualize it (create an image you can view)
To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product takes in the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
cd visualizer 
java -jar visualizer.jar ../generator/sample.mesh sample.svg [-X]
ls -lh sample.svg
```
To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

### Debug Visualizer
In debug mode, it will visualize the polygon's segments and vertices as black, centroid as red and neighbouring relations as grey. Debug mode uses the flag `-X`
 ```
 java -jar visualizer.jar ../generator/sample.mesh sample.svg -X
 ```

### Example runs
To see a examples of the mesh, run any one of the scripts below in the main directory. 

```sh Scripts/runAll```


Or Use Pre-Made Scripts:
The easiest way is to run one of the example scripts from the main directory:


sh Scripts/runTropicalExample
This will generate everything automatically and create a tropical island visualization.

Other examples:

sh Scripts/runDesertExample - Desert island
sh Scripts/runArcticExample - Arctic island
sh Scripts/runSandbox - Simple sandbox mode
sh Scripts/runTemperateExample - Temperate forest island
The output SVG files will be created in the visualizer directory. Just open them in a browser to see your generated islands!

<!--
#### HeatMaps

```
cd island
java -jar island.jar -input (input.mesh) -output (output.mesh) --mode heatmap -shape {circle, rectangle, oval, irregular, random} -altitude {volcanic, cliff, hills, flat, random} -biomes {arctic, tropical, temperate, desert} [-lakes] [-rivers] -soil {dry, humid, wet} [-aquifers] [-seed]
```

Example: `java -jar island.jar -i ../generator/regular.mesh -o desert.mesh --mode heatmap --shape irregular --altitude hills --biomes desert --lakes 2 --rivers 2 -soil dry --aquifers 2 -seed -2116125981790618405`



`shape`: General shape of the island: {circle, rectangle, oval, irregular, random}.\
`altitude` : The altitude behaviour of the island: {volcanic, cliff, hills, flat, random}.\
`biomes`: General biome to use for the whole map: {arctic, tropical, temperate, desert}.\
`lakes`: Maximum number of lakes in the island: (positive integer).\
`rivers`: Maximum number of rivers in the island: (positive integer).\
`soil`: The soil profile to apply to the whole island: {dry, humid, wet}.\
`aquifers`: Number of aquifers to generate on the island: (positive integer).\
`seed`: Seed to use for all random variables: (long type)


##### Whittaker Diagrams
The following graphs are the Whittaker diagrams used in this project. Note: These Whittaker diagrams are not accurate to real the world and are just created by our team for this project. The colors on the diagram also correspond to the colors to the generated map. Note: darker looking tiles are area where humidity levels are higher. 

![My Image](images/arcticWhittaker.png)

![My Image](images/tropicalWhittaker.png)

![My Image](images/temperateWhittaker.png)

![My Image](images/desertWhittaker.png)

##### Other Map Colors

![My Image](images/otherMapColor.png)

### Visualizer



![My Image](images/grid.png)

```sh Scripts/runAllDebug```

![My Image](images/gridDebug.png)


```sh Scripts//runAllDebugIrregular```

![My Image](images/irregularDebug.png)

```sh Scripts/runSandbox```

![My Image](images/sandbox.png)

```sh Scripts/runArcticExample```

![My Image](images/arctic.png)



```sh Scripts/runTemperateExample```

![My Image](images/temperate.png)

```sh Scripts/runDesertExample```

![My Image](images/desert.png)

```sh Scripts/runTemperateHeatmap```

![My Image](images/heatmap.png)
-->
