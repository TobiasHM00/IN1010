public class SortRute extends Rute{
    public SortRute(int rad, int kolonne, String tegn, Labyrint lab) {
        super(rad, kolonne, tegn, lab);
    }

    @Override
    public String toString() {
        return tegn;
    }

    @Override
    public void finn(Rute fra) {
        if(fra == null){
            System.out.println("Ikke lov aa starte paa en sort rute");
        }
    }
}