public class Apning extends HvitRute{

    public Apning(int rad, int kolonne, String tegn, Labyrint labyrint) {
        super(rad, kolonne, tegn, labyrint);
    }
    
    @Override
    public void finn(Rute fra){
        tall = teller;
        teller++;
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