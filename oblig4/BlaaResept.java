public class BlaaResept extends Resepter{
    private static double rabatt = 0.25;
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public int prisAaBetale(int prisPaalegemiddel){
        prisPaalegemiddel = (int) (prisPaalegemiddel*rabatt);
        return prisPaalegemiddel;
    }
}