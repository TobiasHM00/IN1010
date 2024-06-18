import java.util.Iterator;

abstract class Lenkeliste<T> implements Liste<T>{
    protected Node forste,siste;
    protected int stoerrelse = 0;

    class Node{
        protected Node neste;
        protected T type; 
        
        public Node(T type){
            this.type = type;
        }
    }

    private class LenkelisteIterator implements Iterator<T>{
        Node peker = forste;

        @Override
        public boolean hasNext(){
            return peker != null;
        }

        @Override
        public T next() {
            Node tmp = peker;
            peker = peker.neste;
            return tmp.type;
        }
    }

    public Iterator<T> iterator(){
        return new LenkelisteIterator();
    }
    
    @Override
    public int stoerrelse(){
        return stoerrelse;
    }

    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        stoerrelse++;
        if(forste == null){
            forste = nyNode;
            siste = forste;
            return;
        }
        siste.neste = nyNode;
        siste = nyNode;
    }

    @Override
    public T hent(){
        if(forste == null) return null;
        return forste.type;
    }

    @Override
    public T fjern(){
        if(forste == null) throw new UgyldigListeindeks(stoerrelse);
        Node tmp = forste;
        forste = forste.neste;
        stoerrelse--;
        return tmp.type; 
    }

    @Override
    public String toString(){
        String string = "";
        Node tmp = forste;
        if(tmp == null) return "Tom liste";
        while(tmp != null){
            string += tmp.type + " ";
            tmp = tmp.neste;
        }
        return string; 
    }
}