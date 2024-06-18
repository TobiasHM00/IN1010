public class P_Resepter extends HvitResept{
    private static int rabatt = 108;
    public P_Resepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public double prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel - rabatt;
    }
}