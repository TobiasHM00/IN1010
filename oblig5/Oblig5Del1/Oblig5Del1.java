import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister register = new SubsekvensRegister();
        if(args.length <= 0){
            System.out.println("Ingen parametere i args, bruk en fil sånn her: >java Oblig5Del1 \"mappenavnet\" du vil hente metadata filen fra");
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
        int antFiler = 0;
        while(sc.hasNext()){
            String linje = sc.nextLine();
            String[] biter = linje.split(",");
            register.settInn(SubsekvensRegister.lesfil(args[0], biter[0]));
            antFiler++;
        }
        System.out.println("Ferdig med aa lese " + antFiler + " filer");
        sc.close();
        
        HashMap<String,Subsekvens> hash1 = null;
        HashMap<String,Subsekvens> hash2 = null;

        while(register.storrelse() > 0){
            if(hash1 == null && hash2 == null){
                hash1 = register.fjern();
                hash2 = register.fjern();
            } else hash2 = register.fjern();
            hash1 = SubsekvensRegister.slasammen(hash1, hash2);
        }
        register.settInn(hash1);
        System.out.println("Ferdig med sammenslaaing");

        HashMap<String,Subsekvens> hash = register.fjern();
        Subsekvens storsteSub = null;

        for(Subsekvens sub : hash.values()){
            if(storsteSub == null || sub.hentAntForekomster() > storsteSub.hentAntForekomster()){
                storsteSub = sub;
            }
        }
        System.out.println("Storste sub: " + storsteSub);
    }
}