public abstract class Rute {
    protected Rute north, south, east, west;
    protected int radNr, kolonneNr;
    private Labyrint labyrint;
    protected String tegn;

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

    public abstract void finn(Rute fra);
}