package ca.mcmaster.cas.se2aa4.a3.island.Modes;

public enum ModeType {
    SANDBOX, REGULAR, HEATMAP;

    public String toString(){
        switch(this){
        case SANDBOX :
            return "sandbox";
        case REGULAR :
            return "regular";
        case HEATMAP:
            return "heatmap";
        }
        return null;
    }
}
