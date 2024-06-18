public class Tuppel {
    private int r;
    private int k;

    public Tuppel(int r, int k){
        this.r = r;
        this.k = k;
    }

    @Override
    public String toString() {
        return "("+r+","+k+")";
    }
}