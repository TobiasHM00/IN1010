import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Hele {
    public static void main(String[] args) {
        /*
            Når jeg kjører så virker det som at trådene bare står å venter, vet ikke hvordan jeg skal fikse det
            Det skjer når jeg skal prøve å flette hashmap-ene sammen
        */

        ArrayList<String> filerMedTure = new ArrayList<>();
        ArrayList<String> filerMedFalse = new ArrayList<>();

        int antFilerTrue = 0;
        int antFilerFalse = 0;

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

            if(biter[1].equals("True")){
                filerMedTure.add(filnavn);
                antFilerTrue++;
            } else{
                filerMedFalse.add(filnavn);
                antFilerFalse++;
            }
        }
        sc.close();

        Monitor2 monitorTrue = new Monitor2(antFilerTrue-1);
        Monitor2 monitorFalse = new Monitor2(antFilerFalse-1);
        final int ANTALLFLETTETRAADER = 8;

        Thread[] lesere = new Thread[antFilerFalse + antFilerTrue];
        Thread[] flettere = new Thread[ANTALLFLETTETRAADER];

        System.out.println("Starter lesing av filer");
        for(int i = 0; i < antFilerTrue; i++){ // skal starte tråder for å lese filene for de som har blitt smittet
            lesere[i] = new Thread(new LeseTrad(monitorTrue, filerMedTure.get(i)));
            lesere[i].start();
        }

        for(int i = 0; i < antFilerFalse; i++){ //skal starte tråder for å lese filene for de som ikke har blitt smittet
            lesere[i] = new Thread(new LeseTrad(monitorFalse, filerMedFalse.get(i)));
            lesere[i].start();
        }

        System.out.println("Starter fletting");
        for(int i = 0; i<ANTALLFLETTETRAADER; i++){ //starter tråder for å flette 
            flettere[i] = new Thread(new FletteTrad(monitorTrue));
            flettere[i] = new Thread(new FletteTrad(monitorFalse));
            flettere[i].start();
        }

        for(Thread les : lesere){ //skal samle alle lesetrådene
            try {
                les.join();
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Lese traad interrupted");
            }
        }
        
        for(Thread flett : flettere){ //skal samle alle flettetrådene
            try {
                flett.join();
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Flette traad interrupted");
            }
        }

        System.out.println("Ferdig med sammenslaaing");

        // Binomialtest
        System.out.println("------Binomialtest------");
        HashMap<String,Subsekvens> hashTrue = monitorTrue.getArrayList().get(0);
        HashMap<String,Subsekvens> hashFalse = monitorFalse.getArrayList().get(0);

        HashMap<String,Subsekvens> dominanteSekvenser = new HashMap<>();
        double pValue;

        for(String subTrue : hashTrue.keySet()){
            if(hashFalse.containsKey(subTrue)){
                pValue = BinomialTest.test(hashTrue.get(subTrue).hentAntForekomster() + hashFalse.get(subTrue).hentAntForekomster(), hashTrue.get(subTrue).hentAntForekomster());
            } else{
                pValue = BinomialTest.test(hashTrue.get(subTrue).hentAntForekomster(), hashTrue.get(subTrue).hentAntForekomster());
            }

            if(pValue < 0.05){
                dominanteSekvenser.put(subTrue, hashTrue.get(subTrue));
            }
        }

        System.out.println("Dette er de dominante subsekvensene for en som har hatt viruset:\n");
        for(Subsekvens sub : dominanteSekvenser.values()){
            System.out.println(sub);
        }
    }
}