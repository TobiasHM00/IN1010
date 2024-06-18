import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Hovedprogram {
    public static void main(String[] args) {
        Dataklynge saga = lesFraFil("dataklynge.txt");
        skrivUt(saga);
    }

    public static Dataklynge lesFraFil(String filnavn){
        Dataklynge saga = new Dataklynge();
        Scanner sc = null;
        int antNoder;
        int antProsessorerPerNode;
        int minnePerNode;

        try{
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e){
            System.out.println("Fant ikke filen");
        }

        while(sc.hasNextLine()){
            String linje = sc.nextLine();
            String[] biter = linje.split(" ");
            antNoder = Integer.parseInt(biter[0]);
            antProsessorerPerNode = Integer.parseInt(biter[1]);
            minnePerNode = Integer.parseInt(biter[2]);
            for (int i = 0; i < antNoder; i++){
                Node nynode = new Node(antProsessorerPerNode, minnePerNode);
                saga.settInnNode(nynode);
            }
        }
        return saga;
    }

    public static void skrivUt(Dataklynge klynge){
        System.out.println("Noder med minst 64 GB minne: " + klynge.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB minne: " + klynge.noderMedNokMinne(128));
        System.out.println("Noder med minst 512 GB minne: " + klynge.noderMedNokMinne(512));
        System.out.println("Noder med minst 1024 GB minne: " + klynge.noderMedNokMinne(1024));
        System.out.println("Noder med minst 2048 GB minne: " + klynge.noderMedNokMinne(2048));
        System.out.println("Noder med minst 4096 GB minne: " + klynge.noderMedNokMinne(4096));
        System.out.println("Noder med minst 8192 GB minne: " + klynge.noderMedNokMinne(8192) + "\n");

        System.out.println("Antall prosessorer: " + klynge.hentAntProsessorer());
        System.out.println("Antall rack: " + klynge.hentAntRacks());
    }
}