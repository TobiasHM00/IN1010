public class P_Resepter extends HvitResept{
    private static int rabatt = 108;
    public P_Resepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel - rabatt;
    }
}