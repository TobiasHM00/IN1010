public class Bil2 {
    private static int antBiler = 0;
    private int bilnr;
    public Bil2(){
        bilnr = antBiler;
        antBiler++;
    }
    public void skrivUt() {
        System.out.println("Bilnummeret er: " + bilnr);
    }
}