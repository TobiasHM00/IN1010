import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) {
        String filnavn = "labyrinter/" + args[0];
        Labyrint lab = new Labyrint(filnavn);

        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
        String input = sc.nextLine();

        while(!input.contains("-1")){
            String[] biter = input.split(" ");
            int rad = Integer.parseInt(biter[0]);
            int col = Integer.parseInt(biter[1]);

            lab.finnUtveiFra(rad, col);
            
            System.out.println("\nHer er hvordan du gikk gjennom labyrinten");
            System.out.println(lab);
            lab.rens();

            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            input = sc.nextLine();
        }
        System.out.println("Du avsluttet!");
        sc.close();
    }
}
