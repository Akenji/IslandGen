package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.Altitude;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Temperature.Temperature;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandTerrains.Land;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.BodiesWater;

public abstract class Mode {
    String inputMesh;
    String outputMesh;
    ShapeType shape;

    Altitude altitude_gen=new Altitude();

    Temperature temperature_gen=new Temperature();

    AltitudeType altitude;

    Humidity humidity;

    BiomeTypes biome;

    static SoilTypes soil;
    Mesh mesh;
    List<Polygon> polygons;
    List<Segment> segments;
    List<Vertex> vertices;
    List<Tile> tiles;
    List<TileSegment> allSegmentInfoList;
    List<TileSegment> segmentInfoList;
    List<TileSegment> neighbouringSegmentInfoList;
    List<TileVertex> allVerticesInfoList;
    List<TileVertex> verticesInfoList;
    List<TileVertex> centroidInfoList;

    List<BodiesWater> allWater;
    List<Land> allLand;
    String maxLakes;
    static  double width;
    static double height;


    public Mode(String inputMesh, String outputMesh, ShapeType shape, AltitudeType altitude, BiomeTypes biome, String maxLakes, SoilTypes soil){
        this.inputMesh = inputMesh;
        this.outputMesh = outputMesh;
        this.shape = shape;
        this.altitude=altitude;
        this.biome=biome;
        this.maxLakes = maxLakes;
        this.soil=soil;
        this.humidity=new Humidity();
        this.tiles = new ArrayList<>();
        this.allSegmentInfoList = new ArrayList<>();
        this.segmentInfoList = new ArrayList<>();
        this.neighbouringSegmentInfoList = new ArrayList<>();
        this.allVerticesInfoList = new ArrayList<>();
        this.verticesInfoList = new ArrayList<>();
        this.centroidInfoList = new ArrayList<>();
        this.allWater=new ArrayList<>();
        this.allLand=new ArrayList<>();
        width = Double.MIN_VALUE;
        height = Double.MIN_VALUE;
    }

    protected void extractInformation() throws IOException{
        //Extract the information about polygons, segments and vertices from the input mesh
        mesh = new MeshFactory().read(inputMesh);
        polygons = mesh.getPolygonsList();
        segments = mesh.getSegmentsList();
        vertices = mesh.getVerticesList();

        extractVertex();
        extractSegments();
        extractPolygon();
        setNeighbouringTiles();

        //Find the height and width of the mesh
        for (TileVertex v: verticesInfoList) {
            width = (Double.compare(width, v.getX()) < 0? v.getX(): width);
            height = (Double.compare(height, v.getY()) < 0? v.getY(): height);
        }

    }

    private void extractSegments() throws IOException{
        //extract segment information
        String type;
        TileSegment tileSegment;
        for (Segment segment: segments){
            Property segmentType = segment.getProperties(2);
            type = segmentType.getValue();
            if (type.equals("Regular")){
                tileSegment = new TileSegment(segment, vertices, polygons.size());
                tileSegment.setTileVertex1(allVerticesInfoList.get(tileSegment.getVertedIDX1()));
                tileSegment.setTileVertex2(allVerticesInfoList.get(tileSegment.getVertedIDX2()));
                segmentInfoList.add(tileSegment);
                allSegmentInfoList.add(tileSegment);
            }
            else if (type.equals("Neighbouring")){
                tileSegment = new TileSegment(segment, vertices, 0);
                tileSegment.setTileVertex1(allVerticesInfoList.get(tileSegment.getVertedIDX1()));
                tileSegment.setTileVertex2(allVerticesInfoList.get(tileSegment.getVertedIDX2()));
                neighbouringSegmentInfoList.add(tileSegment);
                allSegmentInfoList.add(tileSegment);
            }
            else{
                throw new IOException("Invalid segment type");
            }
        }
    }

    private void extractVertex() throws IOException{
        //extract vertices information
        String type;
        TileVertex tileVertex;
        for(Vertex vertex : vertices){
            Property vertexType = vertex.getProperties(2);
            type = vertexType.getValue();
            tileVertex = new TileVertex(vertex);
            if (type.equals("Regular")){
                verticesInfoList.add(tileVertex);
            }
            else if (type.equals("Centroid")){
                centroidInfoList.add(tileVertex);
            }
            else{
                throw new IOException("Invalid vertex type");
            }
            allVerticesInfoList.add(tileVertex);
        }
    }


    private void extractPolygon(){
        //extract polygon information
        Polygon polygon;
        TileSegment tileSegment; 
        TileVertex tileVertex;

        for (int i = 0; i < polygons.size(); i++) {
            polygon = polygons.get(i);
            Tile tile = new Tile(polygon, segments, vertices, polygons.size());
            tile.setCentroid(centroidInfoList.get(i));

            //Give tile the Tile Segments and Vertices that it holds
            for (Integer j: polygon.getSegmentIdxsList()){
                tileSegment = allSegmentInfoList.get(j);
                tile.addTileSegment(tileSegment);

                //add vertices only if it is not in the list 
                tileVertex = allVerticesInfoList.get(tileSegment.getVertedIDX1());
                if (!tile.isTileVerticesListContains(tileVertex)){
                    tile.addTileVertex(tileVertex);
                }

                tileVertex = allVerticesInfoList.get(tileSegment.getVertedIDX2());
                if (!tile.isTileVerticesListContains(tileVertex)){
                    tile.addTileVertex(tileVertex);
                }
            }

            tiles.add(tile);
        }
    }

    private void setNeighbouringTiles(){
        //give each tile its neighbouring tile
        int id1;
        int id2;
        Tile tile1;
        Tile tile2;
        for (TileSegment tileSegment: neighbouringSegmentInfoList){
            id1 = tileSegment.getVertedIDX1();
            id2 = tileSegment.getVertedIDX2();
            tile1 = tiles.get(id1);
            tile2 = tiles.get(id2);
            tile1.addNeighbouringTileSegment(tileSegment);
            tile1.addNeighbouringTile(tile2);
            tile2.addNeighbouringTileSegment(tileSegment);
            tile2.addNeighbouringTile(tile1);
        }
    }

    public static Double getWidth(){
        return width;
    }

    public static Double getHeight(){
        return height;
    }

    public static SoilTypes getSoil(){
        return soil;
    }

    public Mesh getMesh(){
        //Convert from custom shapes to Struct shapes
        this.polygons = new ArrayList<>();
        this.segments = new ArrayList<>();
        this.vertices = new ArrayList<>();
        List<Vertex> centroids = new ArrayList<>();

        //Polygons
        for (Tile tile: tiles){
            polygons.add(tile.getPolygon());
        }

        //Segments
        for (TileSegment tileSegment: allSegmentInfoList){
            segments.add(tileSegment.getSegment());
        }

        //Vertices
        for (TileVertex tileVertex: centroidInfoList){
            centroids.add(tileVertex.getVertex());
            vertices.add(tileVertex.getVertex());
        }

        for (TileVertex tileVertex: verticesInfoList){
            vertices.add(tileVertex.getVertex());
        }

        
        return Mesh.newBuilder().addAllPolygons(polygons).addAllSegments(segments).addAllVertices(centroids).addAllVertices(vertices).build();
    }

    public abstract void generate();
}
