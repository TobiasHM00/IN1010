import java.util.ArrayList;

public class HvitRute extends Rute{
    public HvitRute(int rad, int kolonne, String tegn, Labyrint lab) {
        super(rad, kolonne, tegn, lab);
    }
    
    @Override
    public String toString() {
        return tegn;
    }

    @Override
    public void finn(Rute fra, ArrayList<Tuppel> sti){
        ArrayList<Tuppel> nysti = new ArrayList<>(sti);
        nysti.add(new Tuppel(this.hentRadNr(),this.hentKolonneNr()));

        if(fra == null){
            north.finn(this,nysti);
            south.finn(this,nysti);
            east.finn(this,nysti);
            west.finn(this,nysti);
        } else if(fra == this.north){
            south.finn(this,nysti);
            east.finn(this,nysti);
            west.finn(this,nysti);
        } else if(fra == this.south){
            north.finn(this,nysti);
            east.finn(this,nysti);
            west.finn(this,nysti);
        } else if(fra == this.east){
            north.finn(this,nysti);
            south.finn(this,nysti);
            west.finn(this,nysti);
        } else if(fra == this.west){
            north.finn(this,nysti);
            south.finn(this,nysti);
            east.finn(this,nysti);
        }
    }
}