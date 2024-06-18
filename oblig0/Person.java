public class Person {
    private Bil3 minBil;
    public Person(Bil3 bil){
        minBil = bil;
    }
    public void skrivUt(){
        System.out.println("Bil nummeret er: " + minBil.hentNummer());
    }
}