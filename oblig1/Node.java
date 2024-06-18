public class Node {
    private int antProsessorer;
    private int minneStoerrelse;
    public Node(int prosessorer, int minne){
        this.antProsessorer = prosessorer;
        this.minneStoerrelse = minne;
    }
    public int hentAntProsessorer(){
        return antProsessorer;
    }
    public boolean nokMinne(int paakrevdMinne){
        if (paakrevdMinne <= minneStoerrelse){
            return true;
        }
        return false;
    }
}