public class Subsekvens{
    public final String subsekvens;
    private int antForekomster = 1;

    public Subsekvens(String sub){
        subsekvens = sub;
    }

    public String hentSubsekvensStr(){
        return subsekvens;
    }
    
    public int hentAntForekomster(){
        return antForekomster;
    }

    public void oekAntForekomster(){
        antForekomster++;
    }

    public void oekAntForekomsterMerEnnEn(int ant){
        antForekomster += ant;
    }

    @Override
    public String toString(){
        return "("+ subsekvens + "," + antForekomster + ")";
    }
}