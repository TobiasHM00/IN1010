public class MilResept extends HvitResept{
    private static int rabatt = 0;
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public double prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel*rabatt;
    }
}