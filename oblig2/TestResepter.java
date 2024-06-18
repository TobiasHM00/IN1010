public class TestResepter {
    public static void main(String[] args) {
        Narkotisk fentanyl = new Narkotisk("Fentanyl", 500, 7.5, 8);
        Vanedannende opioder = new Vanedannende("Opioder", 300, 5.0, 7);
        VanligLegemiddel pPiller = new VanligLegemiddel("P-piller", 150, 10);
        
        Lege kari = new Lege("Kari");
        
        BlaaResept blaa = new BlaaResept(opioder, kari, 123, 5);
        P_Resepter pResept = new P_Resepter(pPiller, kari, 456, 10);
        MilResept miliResept = new MilResept(fentanyl, kari, 789);

        System.out.println("Reit milresept: " + miliResept.hentReit());
        miliResept.bruk();
        System.out.println("Reit milresept: " + miliResept.hentReit());
        miliResept.bruk();
        System.out.println("Reit milresept: " + miliResept.hentReit());
        miliResept.bruk();
        System.out.println("Reit milresept: " + miliResept.hentReit());
        miliResept.bruk();
        System.out.println("Reit milresept: " + miliResept.hentReit());

        System.out.println(blaa.hentLege());
        System.out.println(pResept.hentLegemiddel());
        System.out.println("Pasient ID til milresepten: " + miliResept.hentPasientId());

        System.out.println("\nPris pasient må betale på fentanyl: " + miliResept.prisAaBetale(miliResept.hentLegemiddel().hentPris()) + "kr");
        System.out.println("Pris pasient må betale på opioder: " + blaa.prisAaBetale(blaa.hentLegemiddel().hentPris()) + "kr");
        System.out.println("Pris pasient må betale på P-piller: " + pResept.prisAaBetale(pResept.hentLegemiddel().hentPris()) + "kr");

        System.out.println("\nFarge på milresept: " + miliResept.farge());
        System.out.println("Farge på blaaresept: " + blaa.farge());
    }
}