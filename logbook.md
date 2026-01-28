| Date | Contributers | Description | 
|:--:|---------------|------|
|  Feb 4, 2023  |  Nirmal        |   Created segments between vertices, and set the color to be the average between the two.    |  
|  Feb 8, 2023  |  Sathurshan        |  Created logbook    |  
|  Feb 8, 2023  |  Sathurshan, Nirmal, Mithun        |  Updated backlog with new business logic from part 2    | 
|  Feb 8, 2023  |  Sathurshan, Nirmal, Mithun        |  Design class structure for part 2    |
|  Feb 8, 2023  |  Sathurshan       |  Provided debug mode via command line   |   
|  Feb 8, 2023  |  Sathurshan       |  Created a Mesh ADT that stores all the polygons   |  
|  Feb 9, 2023  |  Nirmal       |  Created a CustomPolygon Class   |
|  Feb 9, 2023  |  Nirmal       |  Polygons have list of vertices and segments attribute   |
|  Feb 9, 2023  |  Nirmal       |  Polygons have centroid attribute   |
|  Feb 9, 2023  |  Nirmal       |  Generate all unique vertices given specific centroid   |
|  Feb 9, 2023  |  Nirmal       |  Generate all unique segments given the generated vertices   |
|  Feb 9, 2023  |  Sathurshan       |  Created a CustomVertex Class   |    
|  Feb 9, 2023  |  Sathurshan       |  Vertex have transparency attributes   |   
|  Feb 9, 2023  |  Sathurshan       |  Vertices have different thickness (size) attributes   |   
|  Feb 9, 2023  |  Sathurshan       |  Generate the centroid vertices   |   
|  Feb 9, 2023  |  Sathurshan       |  Vertices arrays index by position and not by size   |  
|  Feb 9, 2023  |  Mithun       |  Created a CustomSegments Class   |    
|  Feb 10, 2023  |  Mithun       |  Segments have transparency attributes   |   
|  Feb 10, 2023  |  Mithun       |  Segments have different thickness (size) attributes   |   
|  Feb 10, 2023  |  Mithun       |  Segments have colour attribute for calculated colour   |
|  Feb 10, 2023  |  Mithun       |  Resolved merge conflicts for segment branch   |
|  Feb 10, 2023  |  Nirmal       |  Updated Polygon to use Custom Vertices   |
|  Feb 10, 2023  |  Nirmal       |  Updated RegularMesh to create each indivdual polygon   |
|  Feb 10, 2023  |  Nirmal       |  Updated graphics renderer to draw each individual polygon   |
|  Feb 10, 2023  |  Sathurshan       |  Paid back Technical debt by making visualization classes for vertex and segments   |  
|  Feb 10, 2023  |  Sathurshan       |  Debug mode for visualizing polygons and centroids in a different color |  
|  Feb 12, 2023  |  Nirmal       |  Modified Polygons to use CustomSegments   |
|  Feb 12, 2023  |  Nirmal       |  Generated neighbouring relations for regular polygons   |
|  Feb 13, 2023  |  Mithun       |  Created UML diagrams of generator and visualizer for task 2  |
|  Feb 13, 2023  |  Sathurshan       |  Ensured precision model is in place |  
|  Feb 13, 2023  |  Sathurshan       |  Answered question 2 and 3 of task 2 for the report |
|  Feb 13, 2023  |  Sathurshan       |  Created release 2 on github |    
|  Feb 13, 2023  |  Nirmal       |  Seperated the centroids and vertices into two distinct lists   |
|  Feb 13, 2023  |  Nirmal       |  Outputting neighbours on debug in GraphicsRenderer   |
|  Feb 15, 2023  |  Nirmal, Sathurshan       |  Planning out new features for task 3 business logic |    
|  Feb 15, 2023  |  Sathurshan       |  Got Command line arguments for type of mesh, number of polygons, width and height of canvas and integrated with the code |   
|  Feb 15, 2023  |  Nirmal       |  Added random feature for centroids where they are generated at random locations in canvas.   |
|  Feb 15, 2023  |  Nirmal       |  Converted between gen.Polygon and struct.Polygon   |
|  Feb 15, 2023  |  Nirmal       |  Updated IrregularMesh to use GeoStruct, to convert Voronoi Polygons to CustomPolygons   |
|  Feb 15, 2023  |  Nirmal       |  Added new constructor for CustomPolygon for Irregular Polygons.   |
|  Feb 16, 2023  |  Sathurshan       |  Fixed merge conflicts |    
|  Feb 16, 2023  |  Sathurshan       |  Adding documentation for new command line arguments |  
|  Feb 16, 2023  |  Sathurshan       |  Fix indexing of centroids so that llyod relaxation can be applied (technical debt fixed) |  
|  Feb 16, 2023  |  Nirmal       |  Cropped mesh to exptected size   |
|  Feb 17, 2023  |  Sathurshan       |  Applied Llyod Relaxation |  
|  Feb 17, 2023  |  Sathurshan       |  Appended documentations for automatic scripts |    
|  Feb 17, 2023  |  Nirmal       |  Centering all centroids on mesh   |
|  Feb 17, 2023  |  Nirmal       |  Perfectly resizes to width and height   |
|  Feb 18, 2023  |  Mithun       |  Create delaunay triangulation using polygon centroids |    
|  Feb 19, 2023  |  Mithun       |  Apply delaunay triangulation to check for polygon neighbours   |   
|  Feb 21, 2023  |  Mithun       |  Create segments for the neighbourhood relationships  |
|  Feb 21, 2023  |  Sathurshan       |  Created a commandLineReader class that takes care of all the command line operations   |
|  Feb 21, 2023  |  Sathurshan       |  Created test cases to check if consecutive segments are stored in polygons and if centroids are printed as red in debug mode   |
|  Feb 21, 2023  |  Sathurshan       |  Created a new command line argument that allows the user to choose grid spacing for grid mesh |
|  Feb 21, 2023  |  Nirmal       |  Restructured the IrregularMesh, CustomPolygon and GeoStruct to fix some technical debt.   |
|  Feb 21, 2023  |  Nirmal       |  Merged Triangulation to main. Now polygons show neighbouring segments in debug mode.   |
|  Feb 22, 2023  |  Sathurshan       |  Added pictures of examples outputs to ReadMe file  |
|  Feb 22, 2023  |  Nirmal       |  Cleaned up the code more, and removed components that were commented out and not used.   |
|  Feb 23, 2023  |  Mithun       |  Created UML diagrams of generator and visualizer for final task  |
|  Feb 23, 2023  |  Mithun       |  Gave justification for our class diagrams and how it aligned with our code design  |
|  Feb 23, 2023  |  Sathurshan       |  Worked on the report for questions from task 3 and conclusion |
|  Feb 23, 2023  |  Sathurshan       |  Created more test cases for vertex class and for checking all vertices are within the set width and height range |
|  Feb 23, 2023  |  Nirmal       |   Focused on CustomPolygon to encapsulate class. Removed all dependency on MeshADT.  |
|  Feb 23, 2023  |  Nirmal       |   Fixed classes to be more composition based, and removed unecessary inheritance.  |
|  Feb 23-25, 2023  |  Nirmal       |   Commented classes in Generator  |
|  Mar 1, 2023  |  Sathurshan       |   Applied Convex Hall since segments were actually not saved in order |
|  Mar 1, 2023  |  Sathurshan       |   Added properties to visualizer subproject to output a colour fill for polygons |
|  Mar 2, 2023  |  Sathurshan       |   Created classes to extract data from .mesh files |
|  Mar 2, 2023  |  Nirmal       |   Delegated responsibility to generate polygon component instead of polygon object itself.  |
|  Mar 2, 2023  |  Nirmal       |   Encapsulated GeneratePolygon more and organized everything into packages.   |
|  Mar 3, 2023  |  Sathurshan       |   Generalized commandLineReader as Interface to be used in other subprojects |
|  Mar 3, 2023  |  Sathurshan       |   Updated Backlog with new features |
|  Mar 4, 2023  |  Mithun       |   Set up the island subproject to begin island generation |
|  Mar 4, 2023  |  Mithun       |   Created island command line reader with starting inputs |
|  Mar 5, 2023  |  Sathurshan       |   Creating Shapes for the island |
|  Mar 5, 2023  |  Sathurshan       |   Creating Sandbox mode |
|  Mar 5, 2023  |  Sathurshan       |   Extracting infromation and creating Tile classes for island sub project |
|  Mar 6, 2023  |  Nirmal       |   Created Altitude command line reader, and base altitude class.   |
|  Mar 6, 2023  |  Nirmal       |   Developed a function for volcanic elevation. Implemented through lamda expressions.   |
|  Mar 6, 2023  |  Sathurshan       |   Neighbouring tiles are accessible |
|  Mar 6, 2023  |  Sathurshan       |   Sandbox mode release created |
|  Mar 8, 2023  |  Nirmal       |   Created new elevation debug mode from command line.   |
|  Mar 11, 2023  |  Nirmal       |   Reorganized the altitude code, and refined elevation mode made previously.   |
|  Mar 12, 2023  |  Sathurshan       |   Same map can be created using a seed |
|  Mar 13, 2023  |  Mithun       |   User can enter maximum number of lakes from the command line. |
|  Mar 13, 2023  |  Sathurshan       |   Test cases for creating the same map using a seed |
|  Mar 13, 2023  |  Sathurshan       |   Islands have oval shape |
|  Mar 13, 2023  |  Sathurshan       |   rectangular islands can be generated at an angle now |
|  Mar 14, 2023  |  Nirmal       |   Implemented feature that sets the water elevation for oceans.   |
|  Mar 14, 2023  |  Sathurshan       |   Irregular shaped islands can be generated |
|  Mar 14, 2023  |  Sathurshan       |   All features of the rivers were generated |
|  Mar 14, 2023  |  Nirmal       |   Restructured altitude code to be more object oriented. Difference between land and water altitude generation.   |
|  Mar 15, 2023  |  Mithun       |  Randomized number of lakes can be generated on the island.  |
|  Mar 16, 2023  |  Nirmal       |   Created a function that calculates the temperature of polygons based on vertex elevation.   |
|  Mar 16, 2023  |  Nirmal       |   Set each tile to have an average temperature. Getters and Setters for temperature where appropriate.   |
|  Mar 16, 2023  |  Nirmal       |   Implemented new heatmap mode for map.   |
|  Mar 18, 2023  |  Nirmal       |   User can enter the soil profile from command line. Sets soil coefficient to whole map.   |
|  Mar 18, 2023  |  Nirmal       |   Assigned soil coefficients to each soil profile. Will be used in humidity calculation.   |
|  Mar 18, 2023  |  Nirmal       |   Implemented a BodiesWater type to facilitate process of calculating humidity from every water source.   |
|  Mar 18, 2023  |  Nirmal       |   Added humidity attribute to the tiles. Land segment humidity is calculated as average between all water sources.  |
|  Mar 19, 2023  |  Nirmal       |   Adjusting the color based on humidity of tiles. Done to visualize the humidity of various tiles.  |
|  Mar 20, 2023  |  Nirmal       |   Set more relative ranges for heatmap calculation.  |
|  Mar 20, 2023  |  Nirmal       |   Added new land wrapper class which is used to differentiate water tiles from land ones. Both now have different attributes. |
|  Mar 20, 2023  |  Nirmal       |   Island now has Humid and Dry possible profiles in addition to wet profile. |
|  Mar 20, 2023  |  Nirmal       |   Setting ocean level to 0. Making lowest land segment always 0 as well. |
|  Mar 20, 2023  |  Nirmal       |   Updated volcanic elevation to have more variations. Random height and spread rates. |
|  Mar 21, 2023  |  Mithun       |   Cleaned up lakes code to avoid merging of generated lakes. |
|  Mar 21, 2023  |  Mithun       |   User can enter number of aquifers from command line. Aquifers can be generated around the island. |
|  Mar 21, 2023  |  Mithun       |  Adjusted certain tile colours to make biomes distinct.  |
|  Mar 21, 2023  |  Sathurshan       |   Created the Artic Biome |
|  Mar 21, 2023  |  Nirmal       |   Island can now have hills elevation. |
|  Mar 21, 2023  |  Nirmal       |   Made temperature Kelvin based. Always stays above 0. Easier calculations. |
|  Mar 21, 2023  |  Nirmal       |   Lake now has levelled elevation. All vertices connecting lakes have same elevation.|
|  Mar 21, 2023  |  Nirmal       |   Considering lake in humidity calculation.|
|  Mar 21, 2023  |  Nirmal       |   Changed lake to only be a wrapper for tile. No more calculations done in Lake, and made Endorheic lakes use this class.|
|  Mar 22, 2023  |  Mithun       |   Added help command information for user regarding island inputs. |
|  Mar 22, 2023  |  Mithun       |   Cleaned up aquifers code to properly generate around island. |
|  Mar 22, 2023  |  Mithun       |  Implemented tropical rain forest map type.  |
|  Mar 22, 2023  |  Mithun       |   Implemented desert map type.  |
|  Mar 22, 2023  |  Mithun       |   Implemented temperate deciduous forest map type. |
|  Mar 22, 2023  |  Mithun       |   Created new tiles such as sand, clay and savanna tiles to visualize the biomes in the different map types. |
|  Mar 22, 2023  |  Nirmal       |   Island now has a flat and random option.|
|  Mar 22, 2023  |  Nirmal       |   Changed the wet,humid and dry coefficient for better biome assignment.|
|  Mar 22, 2023  |  Sathurshan       |   Fixing bugs in the river generator |
|  Mar 22, 2023  |  Sathurshan       |   Created a beach generator to create beaches for specific biomes |
|  Mar 23, 2023  |  Sathurshan       |   Editing readMe to instruct how to run the code and posted some example generations |
|  Mar 23, 2023  |  Nirmal       |   Implemented a way to get all tiles in the test package for island using Fields and Reflection API|
|  Mar 23, 2023  |  Nirmal       |   Implemented test cases for hills, volcanic and cliff elevation.|
|  Mar 23, 2023  |  Mithun       |   Added the valid option inputs to command descriptions for user reference. |