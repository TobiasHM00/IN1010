public abstract class Resepter {
    protected int reit, Id;
    protected Pasient pasient;
    private static int teller = 1;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege; 
    public Resepter(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        Id = teller;
        teller++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit; 
    }
    
    public int hentId(){
        return Id;
    }
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }
    public Lege hentLege(){
        return utskrivendeLege;
    }
    public int hentPasientId(){
        return pasient.hentPasientID();
    }
    public int hentReit(){
        return reit; 
    }

    public boolean bruk(){
        if(reit == 0){
            return false;
        }
        reit--;
        return true; 
    }

    abstract public String farge();
    abstract public int prisAaBetale(int prisPaalegemiddel);

    @Override
    public String toString(){
        String string = "\n----Resept----";
        string += "\nID: " + Id;
        string += "\nFarge resept: " + farge();
        string += "\nPasient ID og navn: " + pasient.hentPasientID() + ", " + pasient.hentNavn();
        string += "\nAntall bruk av resept igjen: " + reit;
        string += "\nPris pasient maa betale: " + prisAaBetale(legemiddel.hentPris()) + "kr";
        string += "\nLegemiddelnavn: " + legemiddel.hentNavn();
        string += "\nUtskrivende lege: " + utskrivendeLege.hentNavn();
        return string + "\n"; 
    }
}