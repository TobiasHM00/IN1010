import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del2B {
    public static void main(String[] args) {
        /*
            Når jeg kjører så virker det som at trådene bare står å venter, vet ikke hvordan jeg skal fikse det
            Det skjer når jeg skal prøve å flette hashmap-ene sammen
        */

        ArrayList<String> filer = new ArrayList<>();
        int antFiler = 0;

        if(args.length <= 0){
            System.out.println("Ingen parametere i args, gjor sånn her: >java Oblig5Del1 \"mappenavnet\" du vil hente metadata filen fra");
            return;
        }

        Scanner sc = null;
        File filen = new File(args[0] + "/metadata.csv");
        try {
            sc = new Scanner(filen);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ingen fil");
            System.out.println("Sorg for at java filene er i samme mappe som testmappene");
            return;
        }

        while(sc.hasNext()){
            String linje = sc.nextLine();
            String[] biter = linje.split(",");
            String filnavn = args[0]+"/"+biter[0];
            filer.add(filnavn);
            antFiler++;
        }
        sc.close();

        Monitor2 monitor = new Monitor2(antFiler-1);
        final int ANTALLFLETTETRAADER = 8;

        Thread[] lesere = new Thread[antFiler];
        Thread[] flettere = new Thread[ANTALLFLETTETRAADER];

        System.out.println("Starter lesing av filer");
        for(int i = 0; i<antFiler; i++){ // skal starte tråder for å lese filene
            lesere[i] = new Thread(new LeseTrad(monitor, filer.get(i)));
            lesere[i].start();
        }

        System.out.println("Starter fletting");
        for(int i = 0; i<ANTALLFLETTETRAADER; i++){ //starter tråder for å flette 
            flettere[i] = new Thread(new FletteTrad(monitor));
            flettere[i].start();
        }

        for(Thread les : lesere){
            try {
                les.join();
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Lese traad interrupted");
            }
        }
        
        for(Thread flett : flettere){
            try {
                flett.join();
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Flette traad interrupted");
            }
        }

        System.out.println("Ferdig med sammenslaaing");

        ArrayList<HashMap<String,Subsekvens>> list = monitor.getArrayList();
        Subsekvens storsteSub = null;

        for(Subsekvens sub : list.get(0).values()){
            if(storsteSub == null || sub.hentAntForekomster() > storsteSub.hentAntForekomster()){
                storsteSub = sub;
            }
        }
        System.out.println("Storste sub: " + storsteSub);
    }
}