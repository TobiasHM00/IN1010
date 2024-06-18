class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        if(stoerrelse() == 0 || nyNode.type.compareTo(siste.type) > 0){ //sjekker om listen er tom eller veriden er større enn den siste
            super.leggTil(x);
            return;
        }
        
        if(nyNode.type.compareTo(forste.type) < 0){ //sjekker om veriden er mindre enn den første
            nyNode.neste = forste;
            forste = nyNode;
            stoerrelse++;
            return;
        }
        
        Node forrige = null;
        Node tmp = forste;
        while(nyNode.type.compareTo(tmp.type) > 0){
            forrige = tmp;
            tmp = tmp.neste;
        }

        nyNode.neste = tmp;
        forrige.neste = nyNode;
    }
}