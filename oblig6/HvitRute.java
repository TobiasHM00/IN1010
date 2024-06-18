public class HvitRute extends Rute{
    public HvitRute(int rad, int kolonne, String tegn, Labyrint lab) {
        super(rad, kolonne, tegn, lab);
    }
    
    @Override
    public String toString() {
        if(tall == 0){
            return tegn;
        } else return "" + tall;
    }

    @Override
    public void finn(Rute fra){
        tall = teller;
        teller++;
        if(fra == null){
            north.finn(this);
            south.finn(this);
            east.finn(this);
            west.finn(this);
        } else if(fra == this.north){
            south.finn(this);
            east.finn(this);
            west.finn(this);
        } else if(fra == this.south){
            north.finn(this);
            east.finn(this);
            west.finn(this);
        } else if(fra == this.east){
            north.finn(this);
            south.finn(this);
            west.finn(this);
        } else if(fra == this.west){
            north.finn(this);
            south.finn(this);
            east.finn(this);
        }
    }
}