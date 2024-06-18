class Pasient {
    private String navn, fodselsnummer;
    private IndeksertListe<Resepter> resepter = new IndeksertListe<>();
    private int Id;
    private static int teller = 1;

    public Pasient(String navn, String fodselsnummer) {
        Id = teller;
        teller++;
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
    }

    public int hentPasientID(){
        return Id;
    }

    public String hentNavn(){
        return navn;
    }

    public String hentFodselsnummer(){
        return fodselsnummer;
    }

    public IndeksertListe<Resepter> hentResepter(){
        return resepter;
    }

    @Override
    public String toString(){
        String string = "\n---Pasient---";
        string += "\nID: " + Id;
        string += "\nPasientnavn: " + navn;
        string += "\nFodselsnummer: " + fodselsnummer;
        string += "\nResepter paa legemiddeler til pasienten: ";
        for(Resepter resept : resepter){
            string += resept.hentLegemiddel().hentNavn() + ", ";
        }
        return string + "\n";
    }

    
}