import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    private static Lock laas = new ReentrantLock();
    private SubsekvensRegister register = new SubsekvensRegister();

    public void settInn(HashMap<String, Subsekvens> hashmap){
        laas.lock();
        try{
            register.settInn(hashmap);
        }finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> fjern(){
        return register.fjern();
    }

    public ArrayList<HashMap<String,Subsekvens>> getArrayList(){
        return register.getArrayList();
    }

    public int storrelse(){
        return register.storrelse();
    }

    public static HashMap<String,Subsekvens> lesfil(String filnavn){
        return SubsekvensRegister.lesfil(filnavn);
    }

    public static HashMap<String, Subsekvens> slasammen(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2){
        return SubsekvensRegister.slasammen(hash1, hash2);
    }
}