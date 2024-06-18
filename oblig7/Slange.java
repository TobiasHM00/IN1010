public class Slange {
    private Node forste, siste, fjernet;

    public void leggTil(int x, int y){
        Node nyNode = new Node(x,y);
        if(forste == null){
            forste = nyNode;
            siste = nyNode;
            return;
        }
        nyNode.neste = forste;
        forste.forrige = nyNode;
        forste = nyNode;
    }

    public Node hent(){
        return forste;
    }

    public void fjern(){
        siste = siste.forrige;
        fjernet = siste.neste;
        siste.neste = null;
    }

    public Node hentFjernet(){
        return fjernet;
    }

    public void leggTilFjernet(){
        siste.neste = fjernet;
        siste = fjernet;
    }
}