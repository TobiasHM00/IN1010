public class LeseTrad implements Runnable{
    private Monitor2 monitor;
    private String filnavn;

    public LeseTrad(Monitor2 m, String filnavn){
        monitor = m;
        this.filnavn = filnavn;
    }

    @Override
    public void run() {
        monitor.settInn(Monitor2.lesfil(filnavn));
        System.out.println(monitor.storrelse());        
        System.out.println("Ferdig med a lese " + filnavn);
    }
}