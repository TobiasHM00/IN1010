public class Bil3 {
    private static int antBiler = 1000;
    private int bilnr;
    public Bil3(){
        bilnr = antBiler;
        antBiler++;
    }
    public int hentNummer() {
        return bilnr;
    }
}