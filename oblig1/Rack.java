public class Rack {
    private static int maksAntNoder = 12;
    private int teller = 0;
    private Node[] nodeListe;
    public Rack(){
        this.nodeListe = new Node[maksAntNoder];
    }
    public boolean settInnNode(Node node){
        if (teller < maksAntNoder){
            nodeListe[teller] = node;
            teller++;
            return true;
        }
        return false;
    }
    public int hentAntProsessorer(){
        int antProsesser = 0;
        for (int i = 0; i < teller; i++){
            antProsesser += nodeListe[i].hentAntProsessorer();
        }
        return antProsesser;
    }
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoderMedNokMinne = 0;
        for (int i = 0; i < teller; i++){
            if (nodeListe[i].nokMinne(paakrevdMinne)){
                antNoderMedNokMinne += 1;
            }
        }
        return antNoderMedNokMinne;
    }
}