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
        return "----Lege---" + "\nNavn: " + navn + 
        "\nKontrollID: " + kontrollID + "\n";
    }
}