public class Controller{
    private GUI gui;
    private Model model;
    private Thread traad;
    private Rettning retning = Rettning.OST;
    private int sleep = 2000;
        
    public Controller(){
        gui = new GUI(this,retning);
        model = new Model(this, gui);
        traad = new Thread(new Teller());
        traad.start();
    }

    class Teller implements Runnable{
        @Override 
        public void run(){
            while(true){
                try{
                    Thread.sleep(sleep);
                } catch (Exception e){
                    return;
                }
                flytt();
            }
        }
    }

    public void flytt(){
        model.flytt(retning);
    }

    public void settRettning(Rettning r){
        this.retning = r;
    }

    public void minkSoving(){
        sleep -= 100;
    }

    public void sluttVinner(){
        traad.interrupt();
        gui.visVinnerMelding();
        System.exit(0);
    }

    public void sluttTaper(){
        traad.interrupt();
        gui.visTaperMelding();
        System.exit(0);
    }
}