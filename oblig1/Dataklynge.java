import java.util.ArrayList;

public class Dataklynge {
    private ArrayList<Rack> listeMedRacks;
    public Dataklynge(){
        this.listeMedRacks = new ArrayList<Rack>();
    }
    public void settInnNode(Node node){
        for (Rack rack : listeMedRacks){
            if (rack.settInnNode(node)){
                return;
            }
        }
        Rack nyRack = new Rack();
        nyRack.settInnNode(node);
        listeMedRacks.add(nyRack);
    }
    public int hentAntProsessorer(){
        int antProsesser = 0;
        for (Rack rack : listeMedRacks){
            antProsesser += rack.hentAntProsessorer();
        }
        return antProsesser;
    }
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoderMedNokMinne = 0;
        for (Rack rack : listeMedRacks){
            antNoderMedNokMinne += rack.noderMedNokMinne(paakrevdMinne);
        }
        return antNoderMedNokMinne;
    }
    public int hentAntRacks(){
        return listeMedRacks.size();
    }
}