import java.util.ArrayList;
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

            ArrayList<ArrayList<Tuppel>> listeMedLister = lab.finnUtveiFra(rad, col);

            System.out.println("");
            int teller = 0;
            ArrayList<Tuppel> kortest = listeMedLister.get(0);
            if(listeMedLister.size() != 0){
                for(ArrayList<Tuppel> utvei : listeMedLister){
                    teller++;
                    if(!(utvei.size() > kortest.size())){
                        kortest = utvei;
                    }
                }
                System.out.println("Korteste utvei: ");
                System.out.println(kortest);
            }
            System.out.println(teller + " loesninger funnet");

            System.out.println("\nSkriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            input = sc.nextLine();
        }
        System.out.println("Du avsluttet!");
        sc.close();
    }
}
