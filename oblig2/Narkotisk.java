public class Narkotisk extends Legemiddel{
    private int narkotiskStyrke;
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        narkotiskStyrke = styrke;
    }
    
    public int hentNarkotiskStyrke(){
        return narkotiskStyrke;
    }

    @Override
    public String toString(){
        String string = "\n----Legemiddel----"; 
        string += "\nID: " + Id; 
        string += "\nNavn: " + navn; 
        string += "\nPris: " + pris + "kr";
        string += "\nVirkestoff: " + virkestoff + "mg";
        string += "\nNarkotisk styrke: " + narkotiskStyrke;
        return string + "\n";
    }
}