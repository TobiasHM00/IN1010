public abstract class Resepter {
    protected int reit, pasientId, Id;
    private static int teller = 1;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege; 
    public Resepter(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        Id = teller;
        teller++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
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
        return pasientId;
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
    abstract public double prisAaBetale(int prisPaalegemiddel);

    @Override
    public String toString(){
        String string = "----Resept----";
        string += "\nID: " + Id;
        string += "\nFarge resept: " + farge();
        string += "\nPasient ID: " + pasientId;
        string += "\nAntall bruk av resept igjen: " + reit;
        string += "\nPris pasient må betale: " + prisAaBetale(legemiddel.hentPris()) + "kr";
        string += "\n" + legemiddel.toString();
        string += "\n" + utskrivendeLege.toString();
        return string + "\n"; 
    }
}