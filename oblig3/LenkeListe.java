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