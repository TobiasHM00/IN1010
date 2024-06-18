public class BlaaResept extends Resepter{
    private static double rabatt = 0.25;
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge(){
        return "Blaa";
    }

    @Override
    public double prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel*rabatt;
    }
}