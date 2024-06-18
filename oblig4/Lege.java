public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resepter> utskrevneResepter;

    public Lege(String navn){
        this.navn = navn;
        utskrevneResepter = new IndeksertListe<>();
    }
    public String hentNavn(){
        return navn;
    }

    public IndeksertListe<Resepter> hentUtskrevneResepter(){
        return utskrevneResepter;
    }

    @Override
    public String toString(){
        String string ="\n----Lege----";
        string += "\nNavn: " + navn;
        string += "\nLegemiddler som legen har skrevet resept paa: ";
        for(Resepter resept : utskrevneResepter){
            string += resept.hentLegemiddel().hentNavn() + ", ";
        }
        return string + "\n";
    }

    @Override
    public int compareTo(Lege annen){
        return this.navn.compareTo(annen.navn);
    }

    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);

        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        pasient.hentResepter().leggTil(hvitResept);
        return hvitResept;
    }

    public MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        
        MilResept milResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milResept);
        pasient.hentResepter().leggTil(milResept);
        return milResept;
    }
    
    public P_Resepter skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        
        P_Resepter pResept = new P_Resepter(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(pResept);
        pasient.hentResepter().leggTil(pResept);
        return pResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);

        if (legemiddel instanceof Narkotisk){
            if (this instanceof Spesialister){
                utskrevneResepter.leggTil(blaaResept);
                pasient.hentResepter().leggTil(blaaResept);
                return blaaResept;
            } else throw new UlovligUtskrift(this, legemiddel);
        }

        utskrevneResepter.leggTil(blaaResept);
        pasient.hentResepter().leggTil(blaaResept);
        return blaaResept;
    }
}