# Backlog

## Definition of Done
A feature is determined as done if it succesfully accomplishes the minimum of the feature without affecting previous features implemented. It shoul not introduce no more than 30 minutes of technical debt. 

status:
Pending (P), Started (S), Blocked (B), Done (D)


## Product Backlog

| Id | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
| F01   |  Create a Mesh with a determined width and height |   Sathurshan   |   02/08/2023    |  02/08/2023   |   D     |
| F02   |  Generate all Vertices for each polygons |  Nirmal    |    02/09/2023   |  02/09/2023   |    D    |
| F03   |  Generate polygons on mesh |  Nirmal    |    02/10/2023   |  02/10/2023   |   D     |
| F04   |  Generate segments on polygon |  Nirmal    |   02/09/2023    |  02/09/2023   |   D     |
| F05   |  Verticies colours have transparency attributes |   Sathurshan   |   02/09/2023    |  02/09/2023   |   D     |
| F06   |  Segment colours have transparency attributes |   Mithun   |   2/10/2023    |  2/10/2023   |    D    |
| F07   |  Show vertices with different thickness |  Sathurshan    |   02/09/2023    |   02/09/2023  |    D    |
| F08   |  Show segments with different thickness |  Mithun    |   2/10/2023    |  2/10/2023  |    D    |
| F09   |  Show segments with calculated colours |   Mithun   |  2/10/2023  |  2/10/2023  |    D    |
| F10   |  Generate centroid vertex of the polygons |  Sathurshan    |  02/09/2023     |   02/09/2023  |  D      |
| F11   |  Generate neighbouring relations of the polygons |  Nirmal    |   02/12/2023    |  02/12/2023   |    D    |
| F12   |  Provide debug mode |   Sathurshan   |   02/08/2023    |  02/08/2023   |    D    |
| F13   |  debug mode: polygon in black |  Sathurshan    |   02/11/2023    |  02/11/2023   |     D   |
| F14   |  debug mode: centroid in red |  Sathurshan    |  02/11/2023     |  02/11/2023   |    D    |
| F15   |  debug mode: neighbourhood relations in light grey |  Mithun    |  02/20/2023     | 02/25/2023    |  D      |
| F16   |  Crop mesh to expected size |   Nirmal   |    02/15/2023   |  02/17/2023   |    D    |
| F17   |  Polygons randomly placed on meshed |  Nirmal   |   02/15/2023    |  02/15/2023   |    D    |
| F18   |  Generate irregular polygons |  Nirmal  |    02/15/2023   |  02/15/2023   |    D    |
| F19   |  Determine neighbourhood relationships using delaunay triangulation | Mithun   |   02/18/2023    |  02/21/2023   |    D    |
| F20   |  Generate neighbouring segments for each polygon | Mithun   |   02/18/2023    |  02/21/2023   |    D    |
| F21   |  Choose type of mesh from command line | Sathurshan   |   02/15/2023    |  02/15/2023   |    D    |
| F22   |  Choose number of polygons from command line |  Sathurshan  |   02/15/2023    |   02/15/2023  |   D     |
| F23   |  Choose relaxation level from command line | Sathurshan   |  02/17/2023     |   02/17/2023  |    D    |
| F24   |  Choose width and height from command line | Sathurshan   |   02/15/2023    |  02/15/2023   |    D    |
| F25   |  Command line have -h (help) option |  Sathurshan  |   02/15/2023    |  02/15/2023   |    D    |
| F26   |  Choose grid box size for gird base mesh from command line |  Sathurshan  |   02/21/2023    |  02/21/2023   |    D    |
| F27   | User can enter map mode with -- mode via command line      | Mithun     | 03/04/2023 | 03/04/2023 | D  |
| F28   | –help mode in the command line gives a description of how to use to command line  | Mithun | 03/04/2023 | 03/04/2023 | D  |
| F29   | Sandbox map mode can be activated  | Sathurshan     | 03/05/2023 | 03/05/2023 | D |
| F30   | Regular map mode can be activated  | Sathurshan     | 03/06/2023 | 03/06/2023 | D |
| F31   | Heat map mode can be activated     | Nirmal     | 03/15/2023 | 03/15/2023 | D  |
| F32   | User can enter the shape of the island with -- shape via command line  | Sathurshan     | 03/05/2023 | 03/05/2023 | D   |
| F33   | Islands have oval shape option   | Sathurshan | 03/13/2023 | 03/13/2023 | D  |
| F34   | Islands have irregular shape option  | Sathurshan | 03/14/2023  | 03/14/2023 | D |
| F35   | Islands have circular shape option      | Sathurshan | 03/05/2023 | 03/05/2023 | D |
| F36   | Islands have a rectangle shape option   | Sathurshan | 03/06/2023 | 03/06/2023 | D |
| F37   | Islands have a ring shape option  | Sathurshan |  |  | P |
| F38   | User can enter general elevation trend using  --altitude through the command prompt | Nirmal     | 03/06/2023 | 03/06/2023 | D |
| F39   | Island altitude has “volcanic” option   | Nirmal     | 03/06/2023 | 03/06/2023 | D   |
| F40   | Island altitude has “hill” option  | Nirmal     | 03/22/2023 | 03/22/2023 | D  |
| F41   | Island altitude has “flat” option  | Nirmal     | 03/22/2023 | 03/22/2023 | D  |
| F42   | Island altitude has “random” option  | Nirmal     | 03/22/2023 | 03/22/2023 | D   |
| F43   | Enter Debug Elevation mode from command line using -XE  | Nirmal     | 03/08/2023 | 03/08/2023 | D   |
| F44   | Each vertex has an associated temperature  | Nirmal     | 03/15/2023 | 03/15/2023 | D        |
| F45   | Every polygon has an average temperature    | Nirmal     | 03/15/2023 | 03/15/2023 | D   |
| F46   | User can enter the maximum number of lakes on the island using —lakes via the command line.   | Mithun     | 03/21/2023  | 03/21/2023  | D  |
| F47   | User can enter the maximum number of rivers on the island using –rivers via the command line. | Sathurshan | 03/15/2023 | 03/15/2023 | D   |
| F48   | Generate rivers on the island    | Sathurshan | 03/15/2023 | 03/15/2023 | D  |
| F49   | Generate endorheic lakes   | Sathurshan | 03/15/2023 | 03/15/2023 | D   |
| F50   | Merge rivers that intersect with each other | Sathurshan | 03/15/2023 | 03/15/2023 | D |
| F51   | User can enter the number of aquifers on the island using —aquifers via the command line.     | Mithun     | 03/21/2023 | 03/21/2023 | D  |
| F52   | Generate aquifers on the island | Mithun     | 03/21/2023 | 03/21/2023 | D   |
| F53   | User can enter soil absorption profile —soil via the command line.   | Nirmal     | 03/18/2023 | 03/18/2023 | D   |
| F54   | Wet soil profile applied to map  | Nirmal     | 03/18/2023 | 03/18/2023 | D     |
| F55   | Humid soil profile applied to map  | Nirmal     | 03/20/2023 | 03/20/2023 | D    |
| F56   | Dry soil profile applied to map     | Nirmal     | 03/20/2023 | 03/20/2023 | D         |
| F57   | Each land segment has a particular soil absorption.     | Nirmal     | 03/18/2023 | 03/18/2023 | D      |
| F58   | Each land segment has particular humidity.   | Nirmal     | 03/20/2023 | 03/20/2023 | D     |
| F59   | Humidity affects color brightness or darkness   | Nirmal     | 03/20/2023 | 03/20/2023 | D   |
| F60   | User can enter the biome type of the island using -–biomes via the command line.   | Mithun     | 03/21/2023 | 03/21/2023 | D |
| F61   | Arctic type map generatable  | Sathurshan     | 03/21/2023 | 03/21/2023 | D  |
| F62   | Tropical Rain Forest type map generatable    | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F63   | Temperate deciduous forest type map generatable  | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F64   | Desert type map generable   | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F65   | All tiles have assigned types for Sandbox     | Sathurshan | 03/06/2023 | 03/06/2023 | D  |
| F66   | All land tiles have assigned biomes in relation to island biomes type  | Sathurshan | 03/23/2023 | 03/23/2023 | D   |
| F67   | Display ocean tiles   | Sathurshan | 03/06/2023 | 03/06/2023 | D  |
| F68   | Display beach tiles   | Sathurshan | 03/06/2023 | 03/06/2023 | D  |
| F69   | Display lake tiles  | Mithun     | 03/21/2023 | 03/21/2023 | D   |
| F70   | Display lagoon tiles  | Sathurshan     | 03/06/2023 | 03/06/2023 | D    |
| F71   | Display ice tiles   | Mithun | 03/21/2023 | 03/21/2023 | D   |
| F72   | Display snow tiles    | Mithun | 03/21/2023 | 03/21/2023 | D    |
| F73   | Display tundra tiles      | Mithun | 03/21/2023  | 03/21/2023 | D     |
| F74   | Display taiga tiles     | Mithun | 03/21/2023  | 03/21/2023  | D  |
| F75   | Display forest tiles     | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F76   | Display jungle tiles    | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F77   | Display swamp tiles    | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F78   | Display grassland tiles     | Mithun   | 03/06/2023  | 03/06/2023 | D   |
| F79   | Display sandy desert tiles    | Mithun     | 03/22/2023 | 03/22/2023 | D   |
| F80   | Display savanna tiles      | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F81   | Display clay tiles    | Mithun     | 03/22/2023 | 03/22/2023 | D    |
| F82   | Output default seed     | Sathurshan | 03/08/2023 | 03/08/2023 | D   |
| F83   | Output all the user’s input parameters   | Mithun     | 03/22/2023 | 03/22/2023 | D  |
| F84   | User can enter the seed to use via -–seed via the command line    | Sathurshan | 03/12/2023 | 03/12/2023 | D   |
| F85   | Generate same seed map     | Sathurshan | 03/12/2023 | 03/12/2023 | D   |
| F86   | Goods for each tile depending on tile conditions  | Mithun     |  |  | P  |
| F87   | Islands have random shape option  | Sathurshan     | 03/13/2023 | 03/14/2023 | D  |