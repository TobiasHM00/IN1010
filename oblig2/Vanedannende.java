public class Vanedannende extends Legemiddel{
    private int vanedannende;
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        vanedannende = styrke;
    }
    
    public int hentVanedannendeStyrke(){
        return vanedannende;
    }

    @Override
    public String toString(){
        String string = "\n----Legemiddel----";
        string += "\nID: " + Id; 
        string += "\nNavn: " + navn; 
        string += "\nPris: " + pris + "kr";
        string += "\nVirkestoff: " + virkestoff + "mg";
        string += "\nVanedannende Styrke: " + vanedannende;
        return string + "\n";
    }
}