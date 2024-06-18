public class Model{
    private Controller controller;
    private GUI gui;
    private int slangeHodePosX, slangeHodePosY;
    private int lengdeteller = 1;
    private Slange slange;

    public Model(Controller c, GUI gui){
        controller = c;
        this.gui = gui;
        slange = new Slange();
        tegnSlange();
        tegnSkatter();
        gui.setLengde(lengdeteller);
    }

    public void flytt(Rettning rettning){
        int currentPosX = slangeHodePosX;
        int currentPosY = slangeHodePosY;
        if(Rettning.NORD == rettning) currentPosX--;
        if(Rettning.SOR == rettning) currentPosX++;
        if(Rettning.OST == rettning) currentPosY++;
        if(Rettning.VEST == rettning) currentPosY--;

        if(sjekkKollisjon(currentPosX,currentPosY) && lengdeteller > 1){
            controller.sluttTaper();
        }

        if(gyldigRute(currentPosX, currentPosY)){
            besokRute(currentPosX, currentPosY);
        } else{
            controller.sluttTaper();
        }
    }

    public void besokRute(int x, int y){
        slange.leggTil(x,y);
        if(gui.erSkatt(x,y)){
            lengdeteller++;
            gui.setLengde(lengdeteller);
        } else {
            slange.fjern();
        }

        slangeHodePosX = x;
        slangeHodePosY = y;
        gui.tegnSlange(slange);
    }

    public boolean gyldigRute(int x, int y){
        if(x < 0 || x >= 12) return false;
        if(y < 0 || y >= 12) return false;    
        return true;
    }

    public void tegnSlange(){
        slangeHodePosX = 5;
        slangeHodePosY = 6;
        slange.leggTil(slangeHodePosX, slangeHodePosY);
        gui.tegnSlange(slange);
    }

    public void tegnSkatter(){
        gui.tegnSkatt();
    }

    public boolean sjekkKollisjon(int x, int y){
        if(gui.sjekkKollisjon(x,y)) return true;
        return false;
    }
}