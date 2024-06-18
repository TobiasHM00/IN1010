public abstract class Rute {
    protected Rute north, south, east, west;
    protected int radNr, kolonneNr;
    protected Labyrint labyrint;
    protected String tegn;
    protected static int teller = 1;
    protected int tall = 0; 

    public Rute(int rad, int kolonne, String tegn, Labyrint lab){
        this.tegn = tegn;
        radNr = rad;
        kolonneNr = kolonne;
        labyrint = lab;
    }

    public void settNaboer(Rute n, Rute s, Rute e, Rute w){
        north = n;
        south = s;
        east = e;
        west = w;
    }

    public String hentTegn(){
        return tegn;
    }

    public void settInts(int en, int nul){
        teller = en;
        tall = nul;
    }

    public abstract void finn(Rute fra);
}