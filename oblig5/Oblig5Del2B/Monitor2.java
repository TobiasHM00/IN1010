import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    private SubsekvensRegister register = new SubsekvensRegister();
    
    private Lock laas = new ReentrantLock();
    private Condition tom = laas.newCondition();

    private int fletteteller;

    public Monitor2(int teller){
        fletteteller = teller;
    }

    public void settInn(HashMap<String, Subsekvens> hashmap){
        laas.lock();
        try{
            register.settInn(hashmap);
            if(register.storrelse() >= 2) {
                System.out.println("Lar traad kjore");
                tom.signal();
            }
        } finally{
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String, Subsekvens>> fjernToHashmaps(){
        laas.lock();
        try{
            if(fletteteller > 0){
                while(register.storrelse() < 2){ 
                    System.out.println("traad staar aa venter");
                    tom.await();
                }
                ArrayList<HashMap<String,Subsekvens>> liste = new ArrayList<>();
                if(register.storrelse() < 2){
                    return null;
                }
                fletteteller--;
                liste.add(register.fjern());
                liste.add(register.fjern());
                return liste;
            } else return null;
        }catch(InterruptedException e){
            System.out.println("[ERROR] Interrupted");
            return null;
        }
        finally{
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String,Subsekvens>> getArrayList(){
        return register.getArrayList();
    }

    public int storrelse(){
        return register.storrelse();
    }

    public int getFletteTeller(){
        return fletteteller;
    }

    public static HashMap<String,Subsekvens> lesfil(String filnavn){
        return SubsekvensRegister.lesfil(filnavn);
    }

    public static HashMap<String, Subsekvens> slasammen(HashMap<String, Subsekvens> hash1, HashMap<String, Subsekvens> hash2){
        return SubsekvensRegister.slasammen(hash1, hash2);
    }
}