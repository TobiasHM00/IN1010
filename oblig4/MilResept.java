public class MilResept extends HvitResept{
    private static int rabatt = 0;
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel*rabatt;
    }
}