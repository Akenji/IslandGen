package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

public enum BiomeTypes {

    ARCTIC("arctic", false), 
    TROPICAL_FORREST("tropical", true), 
    TEMPERATE_FORREST("temperate", false),
    DESERT("desert", false);

    private String name;
    private Boolean containsBeaches;

    private BiomeTypes(String name, Boolean containsBeaches){
        this.name = name;
        this.containsBeaches = containsBeaches;

    }
    public String toString(){
        return name;
    }

    public Boolean doesContaiBeaches(){
        return containsBeaches;
    }

    public GeneralBiome getGeneralBiome(){
        switch (this){
            case ARCTIC:
                return new ArcticGeneralBiome(260.0);
            case TROPICAL_FORREST:
                return new TropicalRainforestGeneralBiome(293.15);
            case TEMPERATE_FORREST:
                return new TemperateForestGeneralBiome(283.0);
            case DESERT:
                return new DesertGeneralBiome(311.0);
        }
        return null;
    }


}
