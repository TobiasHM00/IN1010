public class HvitResept extends Resepter{
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge(){
        return "Hvit";
    }

    @Override
    public double prisAaBetale(int prisPaalegemiddel){
        return prisAaBetale(prisPaalegemiddel);
    }
}