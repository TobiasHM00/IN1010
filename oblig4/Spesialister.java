public class Spesialister extends Lege implements Godkjenningsfritak{
    private String kontrollID;
    public Spesialister(String navn, String kontrollID){
        super(navn);
        this.kontrollID = kontrollID;
    }

    @Override
    public String hentKontollID(){
        return kontrollID;
    }
    
    @Override    
    public String toString(){
        String string ="\n----Lege----";
        string += "\nNavn: " + navn;
        string += "\nKontroll ID: " + kontrollID;
        string += "\nLegemiddler som legen har skrevet resept paa: ";
        for(Resepter resept : utskrevneResepter){
            string += resept.hentLegemiddel().hentNavn() + ", ";
        }
        return string + "\n";
    }
}