public class HvitResept extends Resepter{
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge(){
        return "Hvit";
    }

    @Override
    public int prisAaBetale(int prisPaalegemiddel){
        return prisPaalegemiddel;
    }
}