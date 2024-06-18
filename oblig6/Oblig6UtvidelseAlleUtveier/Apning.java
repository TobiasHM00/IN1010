import java.util.ArrayList;

public class Apning extends HvitRute{

    public Apning(int rad, int kolonne, String tegn, Labyrint labyrint) {
        super(rad, kolonne, tegn, labyrint);
    }
    
    @Override
    public void finn(Rute fra, ArrayList<Tuppel> sti){
        System.out.println("("+radNr+","+kolonneNr+")");
        ArrayList<Tuppel> nysti = new ArrayList<>(sti);
        nysti.add(new Tuppel(this.radNr, this.kolonneNr));
        labyrint.leggTilUtveier(nysti);
        
        if(fra == null){
            if(this.north == null){
                south.finn(this,nysti);
                east.finn(this,nysti);
                west.finn(this,nysti);
            } else if(this.south == null){
                north.finn(this,nysti);
                east.finn(this,nysti);
                west.finn(this,nysti);
            } else if(this.east == null){
                north.finn(this,nysti);
                south.finn(this,nysti);
                west.finn(this,nysti);
            } else if(this.west == null){
                north.finn(this,nysti);
                south.finn(this,nysti);
                east.finn(this,nysti);
            }
        }
    }
}