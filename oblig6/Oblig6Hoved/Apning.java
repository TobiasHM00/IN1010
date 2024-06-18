public class Apning extends HvitRute{

    public Apning(int rad, int kolonne, String tegn, Labyrint lab) {
        super(rad, kolonne, tegn, lab);
    }
    
    @Override
    public void finn(Rute fra){
        System.out.println("("+radNr+","+kolonneNr+")");
        if(fra == null){
            if(this.north == null){
                south.finn(this);
                east.finn(this);
                west.finn(this);
            } else if(this.south == null){
                north.finn(this);
                east.finn(this);
                west.finn(this);
            } else if(this.east == null){
                north.finn(this);
                south.finn(this);
                west.finn(this);
            } else if(this.west == null){
                north.finn(this);
                south.finn(this);
                east.finn(this);
            }
        }
    }
}