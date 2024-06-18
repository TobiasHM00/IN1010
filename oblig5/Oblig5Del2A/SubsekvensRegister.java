import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SubsekvensRegister {
    private ArrayList<HashMap<String,Subsekvens>> hashMapListe = new ArrayList<>();
    

    public void settInn(HashMap<String, Subsekvens> hashmap){
        hashMapListe.add(hashmap);
    }

    public HashMap<String, Subsekvens> fjern(){
        return hashMapListe.remove(0);
    }

    public ArrayList<HashMap<String,Subsekvens>> getArrayList(){
        return hashMapListe;
    }

    public int storrelse(){
        return hashMapListe.size();
    }

    public static HashMap<String,Subsekvens> lesfil(String filnavn){
        Scanner sc = null;
        File fil = new File(filnavn);
        try {
            sc = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Filen ikke funnet!");
        }

        HashMap<String,Subsekvens> nyHash = new HashMap<>();

        while(sc.hasNextLine()){
            String s = sc.nextLine();
            String[] biter = s.split("");
            for(int i = 0; i<biter.length-2;i++){
                String str = biter[i] + biter[i+1] + biter[i+2];

                if(!nyHash.containsKey(str)){
                    nyHash.put(str, new Subsekvens(str));
                }
            }
        }
        sc.close();

        return nyHash;
    }

    public static HashMap<String, Subsekvens> slasammen(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2){
        HashMap<String, Subsekvens> nyhash = new HashMap<>();

        for(Subsekvens sub1 : hash1.values()){
            nyhash.put(sub1.hentSubsekvensStr(), sub1);
        }

        for(Subsekvens sub2 : hash2.values()){
            if(nyhash.containsKey(sub2.hentSubsekvensStr())){
                Subsekvens sub = nyhash.get(sub2.hentSubsekvensStr());
                sub.oekAntForekomster();
            } else {nyhash.put(sub2.hentSubsekvensStr(), sub2);}
        }

        return nyhash;
    }
}