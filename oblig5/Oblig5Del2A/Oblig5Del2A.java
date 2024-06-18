import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2A {
    public static void main(String[] args) {
        Monitor1 monitor = new Monitor1();

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

        ArrayList<String> filer = new ArrayList<>();
        int antFiler = 0;
        while(sc.hasNext()){
            String linje = sc.nextLine();
            String[] biter = linje.split(",");
            String filnavn = args[0]+"/"+biter[0];
            filer.add(filnavn);
            antFiler++;
        }
        sc.close();

        CountDownLatch barriere = new CountDownLatch(antFiler);
        Thread[] filLesere = new Thread[antFiler];
        for(int i = 0; i<antFiler; i++){
            filLesere[i] = new Thread(new LeseTrad(monitor, filer.get(i), barriere));
            filLesere[i].start();
        }

        System.out.println("Antall traader startet: " + antFiler);
        System.out.println("Venter paa at fil lesing skal bli ferdig.");

        try {
            barriere.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ferdig med aa lese " + antFiler + " filer");

        HashMap<String,Subsekvens> hash1 = null;
        HashMap<String,Subsekvens> hash2 = null;

        while(monitor.storrelse() > 0){
            if(hash1 == null && hash2 == null){
                hash1 = monitor.fjern();
                hash2 = monitor.fjern();
            } else hash2 = monitor.fjern();
            hash1 = SubsekvensRegister.slasammen(hash1, hash2);
        }
        
        monitor.settInn(hash1);
        System.out.println("Ferdig med sammenslaaing");

        HashMap<String,Subsekvens> hash = monitor.fjern();
        Subsekvens storsteSub = null;

        for(Subsekvens sub : hash.values()){
            if(storsteSub == null || sub.hentAntForekomster() > storsteSub.hentAntForekomster()){
                storsteSub = sub;
            }
        }
        System.out.println("Storste sub: " + storsteSub);
    }
}