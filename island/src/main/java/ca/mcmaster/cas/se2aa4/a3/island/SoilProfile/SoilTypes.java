package ca.mcmaster.cas.se2aa4.a3.island.SoilProfile;

public enum SoilTypes {
    WET,HUMID,DRY;

    public String toString(){
        switch(this){
            case WET :
                return "wet";
            case HUMID:
                return "humid";
            case DRY:
                return "dry";
        }
        return null;
    }

    public Double getSoilCoefficient(){
        switch(this){
            case WET:
                return 2.4;
            case HUMID:
                return 2.0;
            case DRY:
                return 1.0;
        }
        return null;
    }
}
