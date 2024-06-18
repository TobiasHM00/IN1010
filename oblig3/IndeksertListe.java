class IndeksertListe<T> extends Lenkeliste<T> {
    
    public void leggTil(int pos, T x){
        Node nyNode = new Node(x);
        Node forrige = null;
        Node dennePos = forste;

        if(0 <= pos && pos <= stoerrelse()){
            if(stoerrelse() == 0){
                super.leggTil(x);
                return;
            }

            int teller = 0;
            while(teller != pos){
                forrige = dennePos;
                dennePos = dennePos.neste;
                teller++;
            }
            
            if(forrige == null){
                nyNode.neste = forste;
                forste = nyNode;
            } else if(dennePos == null){
                siste.neste = nyNode;
                siste = nyNode;
            } else{
                nyNode.neste = dennePos;
                forrige.neste = nyNode;
            }
        } else throw new UgyldigListeindeks(pos);
        stoerrelse++;
    }

    public void sett (int pos, T x){
        Node dennePos = forste;

        if(0 <= pos && pos < stoerrelse()){
            int teller = 0;
            while(teller != pos){
                dennePos = dennePos.neste;
                teller++;
            }
            dennePos.type = x;
        } else throw new UgyldigListeindeks(pos);
    }

    public T hent (int pos){
        Node dennePos = forste;
        T verdi;

        if(0 <= pos && pos < stoerrelse()){
            int teller = 0;

            while(teller != pos){
                dennePos = dennePos.neste;
                teller++;
            }

            verdi = dennePos.type;
        } else throw new UgyldigListeindeks(pos);

        return verdi;
    }
    
    public T fjern (int pos){
        Node forrige = null;
        Node dennePos = forste;
        T verdi;

        if(0 <= pos && pos < stoerrelse()){
            int teller = 0;
            while(teller != pos){
                forrige = dennePos;
                dennePos = dennePos.neste;
                teller++;
            }

            verdi = dennePos.type;
            forrige.neste = dennePos.neste;

        } else throw new UgyldigListeindeks(pos);
        stoerrelse--;
        return verdi;
    }
}    