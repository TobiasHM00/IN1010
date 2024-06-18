public abstract class Legemiddel {
    protected String navn;
    protected int Id, pris;
    private static int teller = 1; 
    protected double virkestoff;
    
    public Legemiddel(String navn, int pris, double virkestoff){
        Id = teller;
        teller++;
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }

    public int hentID(){
        return Id;
    }
    public String hentNavn(){
        return navn; 
    }
    public int hentPris(){
        return pris;
    }
    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int nypris){
        pris = nypris;
    }

    @Override
    public String toString(){
        String string = "\n\n----Legemiddel----";
        string += "\nID: " + Id; 
        string += "\nNavn: " + navn; 
        string += "\nPris: " + pris + "kr";
        string += "\nVirkestoff: " + virkestoff + "mg";
        return string;
    }
}