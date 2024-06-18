import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable{
    private Monitor1 monitor;
    private String filnavn;
    private CountDownLatch barriere;

    public LeseTrad(Monitor1 m, String filnavn, CountDownLatch b){
        monitor = m;
        this.filnavn = filnavn;
        barriere = b;
    }

    @Override
    public void run() {
        monitor.settInn(Monitor1.lesfil(filnavn));
        barriere.countDown();
    }
}