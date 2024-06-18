public class TestLegemiddel {
    public static void main(String[] args) {
        Narkotisk narkotisk = new Narkotisk("Fentanyl", 500, 3.5, 8);
        Vanedannende vanedannende = new Vanedannende("Opioder", 350, 2.5, 5);
        VanligLegemiddel vanlig = new VanligLegemiddel("Paracet", 40, 1.5);

        System.out.println("Pris: " + narkotisk.hentPris() + "kr");
        narkotisk.settNyPris(700);
        System.out.println("Nypris: " + narkotisk.hentPris() + "kr");
        System.out.println("Pris: " + vanlig.hentPris() + "kr");
        vanlig.settNyPris(50);
        System.out.println("Nypris: " + vanlig.hentPris() + "kr");

        System.out.println("Virkestoff: " + vanedannende.hentVirkestoff() + "mg");
        System.out.println("Vanedannende styrke: " + vanedannende.hentVanedannendeStyrke());
    }
}