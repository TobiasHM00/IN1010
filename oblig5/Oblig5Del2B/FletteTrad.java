import java.util.ArrayList;
import java.util.HashMap;

public class FletteTrad implements Runnable{
    private Monitor2 monitor;

    public FletteTrad(Monitor2 m){
        monitor = m;
    }
    
    @Override
    public void run() {
        while(monitor.getFletteTeller() > 0){
            ArrayList<HashMap<String,Subsekvens>> list = monitor.fjernToHashmaps();

            if(list != null){
                HashMap<String,Subsekvens> nyhash = Monitor2.slasammen(list.get(0),list.get(1));
                monitor.settInn(nyhash);
            }
        }
    }
}