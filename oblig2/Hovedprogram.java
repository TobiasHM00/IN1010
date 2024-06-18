public class Hovedprogram {
    public static void main(String[] args) {
        Narkotisk fentanyl = new Narkotisk("Fentanyl", 500, 7.0, 8);
        Vanedannende vival = new Vanedannende("Vival", 300, 5.5, 5);
        VanligLegemiddel pPiller = new VanligLegemiddel("P-piller", 150, 10);

        Lege kari = new Lege("Kari");
        Spesialister sara = new Spesialister("Sara", "ef28rev");
        
        BlaaResept blaa = new BlaaResept(vival, kari, 123, 5);
        P_Resepter pResept = new P_Resepter(pPiller, kari, 456, 6);
        MilResept milResept = new MilResept(fentanyl, sara, 789);

        /*skriver ut all info om alle objektene. Skriver ikke ut for hvert legemiddel eller for hver lege for bruker 
        toString metodene til disse inni toString metoden til Resept*/
        System.out.println(blaa.toString());
        System.out.println(pResept.toString());
        System.out.println(milResept.toString());
    }
}