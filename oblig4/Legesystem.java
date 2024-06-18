import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Legesystem {
    private static IndeksertListe<Pasient> pasientliste = new IndeksertListe<>();
    private static IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    private static Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();;
    private static IndeksertListe<Resepter> reseptListe = new IndeksertListe<>();

    public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {
        lesFil("legedata.txt");
        //lesFil("legedatastor.txt");

        meny();
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine().toLowerCase();

        while (!inp.equals("stopp")) {
            //skriver ut en oversikt over alt i systemet
            if (inp.equals("oversikt")) {
                System.out.println(pasientliste);
                System.out.println(legemiddelListe);
                System.out.println(legeListe);
                System.out.println(reseptListe);
            }

            //bruker en resept
            else if (inp.equals("bruk")) {
                Pasient pasient = null;
                System.out.println(pasientliste);

                int valg = 0;
                try {
                    System.out.println("Hvilken pasient vil du se resepter for? Skriv pasientID til personen (1-"+ pasientliste.stoerrelse() +"):");
                    valg = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input. ID maa vaere et heltall");
                }

                try {
                    if(valg < pasientliste.stoerrelse()){
                        for (Pasient p : pasientliste) {
                            if (valg == p.hentPasientID()) {
                                pasient = p;
                                System.out.println("\nValgt pasient: " + pasient.hentNavn()+ " (fnr: " + pasient.hentFodselsnummer() + ")" );
                            }
                        }
                    } else throw new UgyldigListeindeks(valg);
                } catch (UgyldigListeindeks e) {
                    System.out.println("Det finnes ingen pasient med ID-en: " + valg);
                }

                if(pasient != null){
                    IndeksertListe<Resepter> resepter = pasient.hentResepter();
                    
                    try {
                        for(Resepter resepten : resepter){
                            System.out.println(resepten.hentId() + ": " + resepten.hentLegemiddel().hentNavn() + " ("+ resepten.hentReit()+ " reit)");
                        }
                        System.out.println("\nHvilken resept vil du bruke? (skriv respetID): ");
                        valg = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input. ID maa vaere et heltall");
                    }
                    
                    try {
                        if(valg > reseptListe.stoerrelse()) {
                            System.out.println("Denne resepten finnes ikke! ");
                        }
                        for (Resepter r: resepter) {
                            if(valg == r.hentId()) {
                                r.bruk();
                                System.out.println("Brukte resept paa: " + r.hentLegemiddel().hentNavn() + ". gjenstaaende reit: " + r.hentReit());
                            } 
                        }
                    } catch (UgyldigListeindeks e) {
                        System.out.println("Finnes ikke en resept med ID-en: " + valg);
                    } 
                }
            }    
        
            else if (inp.equals("til fil")) { //fullført
                try (FileWriter filSkriver = new FileWriter("tilfiltest.txt")) {
                    filSkriver.write("# Pasienter (navn, fodselsnummer)\n");
                    for(Pasient pasient : pasientliste){
                        filSkriver.write(pasient.hentNavn() + "," + pasient.hentFodselsnummer() + "\n");
                    }

                    filSkriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");

                    for(Legemiddel legemiddel : legemiddelListe){
                        if(legemiddel instanceof Narkotisk){
                            Narkotisk narkotisk = (Narkotisk) legemiddel;
                            filSkriver.write(narkotisk.hentNavn() + ",narkotisk," + narkotisk.hentPris() + "," + narkotisk.hentVirkestoff() + "," + narkotisk.hentNarkotiskStyrke() + "\n");
                        } else if(legemiddel instanceof VanligLegemiddel){
                            VanligLegemiddel vanlig = (VanligLegemiddel) legemiddel;
                            filSkriver.write(vanlig.hentNavn() + ",vanlig," + vanlig.hentPris() + "," + vanlig.hentVirkestoff() + "\n");
                        } else if(legemiddel instanceof Vanedannende){
                            Vanedannende vanedannende = (Vanedannende) legemiddel;
                            filSkriver.write(vanedannende.hentNavn() + ",vanedannende," + vanedannende.hentPris() + "," + vanedannende.hentVirkestoff() + "," + vanedannende.hentVanedannendeStyrke() + "\n");
                        }
                    }

                    filSkriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");

                    for(Lege lege : legeListe){
                        if(lege instanceof Spesialister){
                            Spesialister spes = (Spesialister) lege;
                            filSkriver.write(spes.hentNavn() + "," + spes.hentKontollID() + "\n");
                        } else{
                            filSkriver.write(lege.hentNavn() + ",0\n");
                        }
                    }

                    filSkriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");

                    for(Resepter resept : reseptListe){
                        if(resept instanceof HvitResept){
                            HvitResept hvit = (HvitResept) resept;
                            filSkriver.write(hvit.hentLegemiddel().hentID() + "," + hvit.hentLege().hentNavn() + "," + hvit.hentPasientId() + ",hvit," + hvit.hentReit() + "\n");
                        } else if(resept instanceof MilResept){
                            MilResept mil = (MilResept) resept;
                            filSkriver.write(mil.hentLegemiddel().hentID() + "," + mil.hentLege().hentNavn() + "," + mil.hentPasientId() + ",militaer," + mil.hentReit() + "\n");
                        } else if(resept instanceof P_Resepter){
                            P_Resepter p = (P_Resepter) resept;
                            filSkriver.write(p.hentLegemiddel().hentID() + "," + p.hentLege().hentNavn() + "," + p.hentPasientId() + ",p," + p.hentReit() + "\n");
                        } else{
                            BlaaResept blaa = (BlaaResept) resept;
                            filSkriver.write(blaa.hentLegemiddel().hentID() + "," + blaa.hentLege().hentNavn() + "," + blaa.hentPasientId() + ",blaa," + blaa.hentReit() + "\n");
                        }
                    }

                    filSkriver.close();
                    System.out.println("Alt ble skrevet til en ny fil!");
                } catch (IOException e) {
                    System.out.println("Alt ble ikke skrevet til en ny fil.");
                    e.printStackTrace();
                }
            } 
            //oppretter og legger til objekt
            else if (inp.equals("opprett")) {
                System.out.println("\nHvilke type objekt vil du lage? (lege, legemiddel, resept eller pasient) ");
                inp = sc.nextLine().toLowerCase();
                            
                if(inp.equals("resept")){
                    Pasient valgtPasientObj = null;
                    System.out.println("\nVelg en pasisent du vil skrive resepten paa (skriv ID-en til pasienten, 1-"+ pasientliste.stoerrelse() +"):");
                    int valgtPasientInt = Integer.parseInt(sc.nextLine());

                    try {
                        if(valgtPasientInt <= pasientliste.stoerrelse()){
                            for(Pasient pasient : pasientliste){
                                if(valgtPasientInt == pasient.hentPasientID()){
                                    valgtPasientObj = pasient;
                                    System.out.println("Pasient funnet!");
                                }
                            }
                        } else throw new UgyldigListeindeks(valgtPasientInt);
                    } catch (UgyldigListeindeks e) {
                        System.out.println("Finner ikke en pasient med ID-en: " + valgtPasientInt);
                    }

                    Legemiddel valgtLegemiddelObj = null;
                    System.out.println("\nVelg et legemiddel resepten skal gjelde for (skriv ID-en til legemiddelet, 1-"+ legemiddelListe.stoerrelse() +"):");
                    int valgtLegemiddelInt = Integer.parseInt(sc.nextLine());

                    try {
                        if (valgtLegemiddelInt <= legemiddelListe.stoerrelse()) {
                            for(Legemiddel legemiddel : legemiddelListe){
                                if(valgtLegemiddelInt == legemiddel.hentID()){
                                    valgtLegemiddelObj = legemiddel;
                                    System.out.println("Legemiddel funnet!");
                                }
                            }
                        } else throw new UgyldigListeindeks(valgtLegemiddelInt);
                    } catch (UgyldigListeindeks e) {
                        System.out.println("Finner ikke et legemiddel med ID-en: " + valgtLegemiddelInt);
                    }
                    
                    for(Lege lege : legeListe){
                        System.out.println("-"+lege.hentNavn());
                    }
                    Lege valgtLegeObj = null;
                    System.out.println("\nSkriv navnet paa legen som skal skrive ut resepten:");
                    String valgtLegeStr = sc.nextLine();

                    for(Lege lege : legeListe){
                        if(lege.hentNavn().contains(valgtLegeStr)){
                            valgtLegeObj = lege;
                            System.out.println("Lege funnet!");
                        }
                    }

                    if(valgtLegeObj != null && valgtLegemiddelObj != null && valgtPasientObj != null){
                        System.out.println("Hvilken type resept vil du skrive? (blaa, hvit, mili eller p)");
                        String resept = sc.nextLine().toLowerCase();

                        if(resept.equals("blaa")){
                            int antallReit = 0;
                            try {
                                System.out.println("\nSkriv anatll ganger resepten kan brukes:");
                                antallReit = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Ugyldig input. Reit skal vaere et heltall.");
                            }

                            if(antallReit < 0){
                                System.out.println("Kan ikke bruke negativ reit.");
                            } else {
                                try {
                                    Resepter nyResept = valgtLegeObj.skrivBlaaResept(valgtLegemiddelObj, valgtPasientObj, antallReit);
                                    reseptListe.leggTil(nyResept);
                                    System.out.println("Blaaresept lagt til!");
                                } catch (UlovligUtskrift e) {
                                    System.out.println("Ulovlig utskrift");
                                }
                            }
                        } 
                        else if(resept.equals("hvit")){
                            int antallReit = 0;
                            try {
                                System.out.println("\nSkriv anatll ganger resepten kan brukes:");
                                antallReit = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Ugyldig input. Reit skal vaere et heltall.");
                            }

                            if(antallReit < 0){
                                System.out.println("Kan ikke bruke negativ reit.");
                            } else {
                                try {
                                    Resepter nyResept = valgtLegeObj.skrivHvitResept(valgtLegemiddelObj, valgtPasientObj, antallReit);
                                    reseptListe.leggTil(nyResept);
                                    System.out.println("Hvitresept lagt til!");
                                } catch (UlovligUtskrift e) {
                                    System.out.println("Ulovlig utskrift");
                                }
                            }   

                        } 
                        else if(resept.equals("mili")){
                            try {
                                Resepter nyResept = valgtLegeObj.skrivMilResept(valgtLegemiddelObj, valgtPasientObj);
                                reseptListe.leggTil(nyResept);
                                System.out.println("Mili-Resept lagt til!");
                            } catch (UlovligUtskrift e) {
                                System.out.println("Ulovlig utskrift");
                            }

                        } 
                        else if(resept.equals("p")){
                            int antallReit = 0;
                            try {
                                System.out.println("\nSkriv anatll ganger resepten kan brukes:");
                                antallReit = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Ugyldig input. Reit skal vaere et heltall.");
                            }

                            if(antallReit < 0){
                                System.out.println("Kan ikke bruke negativ reit.");
                            } else {
                                try {
                                    Resepter nyResept = valgtLegeObj.skrivPResept(valgtLegemiddelObj, valgtPasientObj, antallReit);
                                    reseptListe.leggTil(nyResept);
                                    System.out.println("P-Resept lagt til!");
                                } catch (UlovligUtskrift e) {
                                    System.out.println("Ulovlig utskrift");
                                }
                            }
                        } 
                        else ugyldig();
                    } else System.out.println("Et eller flere av valgene dine er ikke gyldige :(");
                } 
                else if(inp.equals("lege")){ 
                    System.out.println("\nSkal legen vaere en spesialist? (ja/nei)");
                    String svar = sc.nextLine().toLowerCase();
                    
                    boolean sammeKontId = false;
                    
                    if (svar.equals("ja")){
                        System.out.println("\nSkriv navnet til legen:");
                        String navn = sc.nextLine();
                        System.out.println("\nSkriv kontroll ID-en til legen:");
                        String kontrollID = sc.nextLine();

                        for(Lege lege : legeListe){
                            if(lege instanceof Spesialister){
                                Spesialister spes = (Spesialister) lege;
                                if(spes.hentKontollID().equals(kontrollID)){
                                    sammeKontId = true;
                                }
                            }
                        }

                        if(!sammeKontId){
                            Spesialister nySpes = new Spesialister(navn, kontrollID);
                            legeListe.leggTil(nySpes);
                            System.out.println("Spesialist ble lagt til!");
                        } else {
                            System.out.println("Det finnes allerede en Spesialist med kontroll ID-en: " + kontrollID);
                            System.out.println("Spesialist ble ikke lagt til");
                        }    

                    } else if(svar.equals("nei")){                          
                        System.out.println("\nSkriv navnet til legen:");
                        String navn = sc.nextLine();

                        Lege nylege = new Lege(navn);
                        legeListe.leggTil(nylege);
                        System.out.println("Lege ble lagt til!");

                    } else ugyldig();
                }
                else if(inp.equals("legemiddel")){ 
                

                    System.out.println("\nHvilke type legemiddel vil du legge til? (narkotisk, vanlig, vanedannende):");
                    String legemiddel = sc.nextLine().toLowerCase();
                    System.out.println("\nSkriv navnet til legemiddelet:");
                    String navn = sc.nextLine();

                    int pris = 0;
                    try {
                        System.out.println("\nSkriv prisen til legemiddelet (uten kr):");
                        pris = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input paa pris, maa vaere et tall");
                    }

                    double virkestoff = 0;
                    try {
                        System.out.println("\nSkriv hvor mye virkestoff det er i legemiddelet (uten benevning):");
                        virkestoff = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input paa virkestoff, virkestoff maa vaere et flyttall");
                    }

                    if(legemiddel.equals("narkotisk")){                            
                        int narkotiskStryke = 0;
                        try {
                            System.out.println("\nSkriv den narkotiske styrken til legemiddelet:");
                            narkotiskStryke = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Ugyldig input paa natorkisk styrke, narkotisk styrke maa vaere et tall");
                        }
                        
                        if(narkotiskStryke >= 0 && virkestoff > 0 && pris >= 0){
                            Narkotisk nyttNarkotisk = new Narkotisk(navn, pris, virkestoff, narkotiskStryke);
                            legemiddelListe.leggTil(nyttNarkotisk);
                            System.out.println("Narkotiks legemiddel ble lagt til!");
                        }

                    } else if(legemiddel.equals("vanlig")){
                        if(virkestoff > 0 && pris >= 0){
                            VanligLegemiddel nyttLegemiddel = new VanligLegemiddel(navn, pris, virkestoff);
                            legemiddelListe.leggTil(nyttLegemiddel);
                            System.out.println("Vanlig legemiddel ble lagt til!");
                        }

                    } else if(legemiddel.equals("vanedannende")){
                        int vanedannendestyrke = 0;
                        try {
                            System.out.println("\nSkriv den vanedannende styrken til legemiddelet:");
                            vanedannendestyrke = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Ugyldig input paa vanedannende styrke, vandennende styrke maa vaere et tall");
                        }

                        if(vanedannendestyrke >= 0 && virkestoff > 0 && pris >= 0){
                            Vanedannende nyttVanedannende = new Vanedannende(navn, pris, virkestoff, vanedannendestyrke);
                            legemiddelListe.leggTil(nyttVanedannende);
                            System.out.println("Vanedannede legemiddel ble lagt til!");
                        }
                    }
                }
                else if(inp.equals("pasient")){ 
                    
                    
                    System.out.println("\nSkriv pasienten sitt navn:");
                    String navn = sc.nextLine();
                    System.out.println("\nSkriv fodselsnummeret til pasienten:");
                    String fodselsnummer = sc.nextLine();
                    boolean sammeFod = false;

                    for(Pasient pasient : pasientliste){
                        if(fodselsnummer.equals(pasient.hentFodselsnummer())){
                            sammeFod = true;
                        }
                    }
                    
                    if(!sammeFod){
                        Pasient nyPasient = new Pasient(navn, fodselsnummer);
                        pasientliste.leggTil(nyPasient);
                        System.out.println("Pasient ble lagt til!");
                    } else {
                        System.out.println("Det finnes allerede en pasient med dette fodselsnummeret.");
                        System.out.println("Pasienten ble ikke lagt til");
                    }
                }
                else ugyldig();
            } 
            //vise en statsetikk over legesystemet
            else if (inp.equals("statestikk")) {
                int vanedannendeAnt = 0; //teller opp antall resepter skrevet ut med vanedannende
                for (Resepter resepter : reseptListe) {
                    if (resepter.hentLegemiddel() instanceof Vanedannende) {
                        vanedannendeAnt++;
                    }
                }
                System.out.println("\nAntall resepter med vanedannende legemiddel er: " + vanedannendeAnt);
                
                int narkotiskeAnt = 0;
                for (Resepter resepter : reseptListe) { //teller opp narkotiske ustkrevne resepter
                    if (resepter.hentLegemiddel() instanceof Narkotisk)
                    narkotiskeAnt++;
                }
                System.out.println("Antall resepter med narkotisk legemiddel er: " + narkotiskeAnt + "\n");

                for (Lege leger : legeListe) {
                    int antallRes = 0; //henter ut leger som har skrevet ut narkotiske resepter
                    for (Resepter resept : leger.hentUtskrevneResepter()) {
                        if (resept.hentLegemiddel() instanceof Narkotisk) {
                            antallRes++;
                        }
                    }
                    if (antallRes > 0) {
                        System.out.println(leger.hentNavn() + " har " + antallRes + " resepter paa narkotiske legemidler.");
                    }
                }
                System.out.println("");

                for (Pasient pasienter : pasientliste) { //skriver ut pasienter som har respepter paa narkotiske legemidler
                    int antallRes = 0;
                    for (Resepter resept : pasienter.hentResepter()) {
                        if (resept.hentLegemiddel() instanceof Narkotisk) {
                            antallRes++;
                        }
                    }
                    if (antallRes > 0) {
                        System.out.println(pasienter.hentNavn() + " har " + antallRes + " resepter paa narkotiske legemidler. ");
                    }
                }
            }
            //skriver ut om det er en ugyldig kommando
            else ugyldig();

            meny();
            inp = sc.nextLine();
        }
        sc.close(); 

        System.out.println("Du stoppet programmet!");
    }
    
    //forklarende meny
    public static void meny(){
        System.out.println("\nSkriv \"oversikt\", for aa skrive ut all informasjonen og faa en oversikt.");
        System.out.println("Skriv \"opprett\", for aa opprette og legge til nye elementer i systemet.");
        System.out.println("Skriv \"bruk\", for aa bruke en resept.");
        System.out.println("Skriv \"til fil\", for aa skrive alt til en fil.");
        System.out.println("Skriv \"statestikk\", for en statestikk oversikt.");
        System.out.println("Skriv \"stopp\", for aa stoppe programmet.");
        System.out.println("\nHva vil du gjore?");
    }

    //metode som skriver ut utgyldig input
    public static void ugyldig(){
        System.out.println("\n---Ugyldig input---");
    }

    //leser fra fil
    public static void lesFil(String filnavn) throws NumberFormatException, UlovligUtskrift{        
        int teller = 0;
        Scanner sc = null;

        try{
            File myObj = new File(filnavn);
            sc = new Scanner(myObj);
        } catch (FileNotFoundException e){
            System.out.println("Fant ikke filen " + filnavn);
        }

        while (sc.hasNextLine()){
            String linje = sc.nextLine();
            String[] biter = linje.strip().split(",");
            int pos = 0;
            for(String bit : biter){
                biter[pos] = bit.strip();
                pos++;
            }
            //skal øke telle der første char er #
            if (linje.charAt(0) == '#') {
                teller++;
            } else {
                //skal lage pasient der teller er 1
                if (teller == 1) {
                    Pasient pasient = new Pasient(biter[0], biter[1]);
                    pasientliste.leggTil(pasient);
                } 
                //skal lage legemiddel der teller er 2
                
                else if (teller == 2) {
                    int pris = (int) Math.ceil(Double.parseDouble(biter[2]));
                    double virkestoff = Double.parseDouble(biter[3]);
                    int styrke = 0;

                    if(biter.length == 5){
                        styrke = Integer.parseInt(biter[4]);
                    }
                                    
                    Legemiddel legemiddel;
                    
                    if (biter[1].equals("narkotisk")) {
                        legemiddel = new Narkotisk(biter[0], pris, virkestoff, styrke);
                        legemiddelListe.leggTil(legemiddel);
                    } else if (biter[1].equals("vanedannende")) {
                        legemiddel = new Vanedannende(biter[0], pris, virkestoff, styrke);
                        legemiddelListe.leggTil(legemiddel);
                    } else if (biter[1].equals("vanlig")) {
                        legemiddel = new VanligLegemiddel(biter[0], pris, virkestoff);
                        legemiddelListe.leggTil(legemiddel);
                    }
                    
                } 
                //skal lage lege der teller er 3
                else if (teller == 3) {
                    Lege lege;
    
                    if (biter[1].equals("0")) {
                        lege = new Lege(biter[0]);
                        legeListe.leggTil(lege);
                    } else {
                        lege = new Spesialister(biter[0], biter[1]);
                        legeListe.leggTil(lege);
                    }
                    
                } 
                //skal lage resept der teller er 4
                else if(teller == 4) {
                    Legemiddel legemiddelObjekt = null;
                    for(Legemiddel legemiddel : legemiddelListe){
                        if(legemiddel.hentID() == Integer.parseInt(biter[0])){
                            legemiddelObjekt = legemiddel;
                        }
                    }

                    Lege legeObjekt = null;
                    for (Lege lege : legeListe){
                        if(lege.hentNavn().equals(biter[1])){
                            legeObjekt = lege;
                        }  
                    }

                    Pasient pasientObjekt = null;
                    for (Pasient pasient : pasientliste) {
                        if (pasient.hentPasientID() == Integer.parseInt(biter[2])) {
                            pasientObjekt = pasient;
                        }
                    }
                    
                    Resepter resept;
                    //vil ikke lage resept der pasient, lege og legemiddel ikke finnes
                    if(pasientObjekt != null && legeObjekt != null && legemiddelObjekt != null){
                        if(biter[3].equals("hvit")){
                            try {
                                resept = legeObjekt.skrivHvitResept(legemiddelObjekt, pasientObjekt, Integer.parseInt(biter[4]));
                                reseptListe.leggTil(resept);
                            } catch (UlovligUtskrift e) {
                            }
                        } 
                        
                        else if (biter[3].equals("blaa")) {
                            try {
                                resept = legeObjekt.skrivBlaaResept(legemiddelObjekt, pasientObjekt, Integer.parseInt(biter[4]));
                                reseptListe.leggTil(resept);
                            } catch (UlovligUtskrift e) {
                            }
                        } 
                        
                        else if (biter[3].equals("militaer")) {
                            try {
                                resept = legeObjekt.skrivMilResept(legemiddelObjekt, pasientObjekt);
                                reseptListe.leggTil(resept);
                            } catch (UlovligUtskrift e) {}
                        } 
                        
                        else if (biter[3].equals("p")) {
                            try {
                                resept = legeObjekt.skrivPResept(legemiddelObjekt, pasientObjekt, Integer.parseInt(biter[4]));
                                reseptListe.leggTil(resept);
                            } catch (UlovligUtskrift e) {
                            }
                        }
                    }
                }
            }   
        }
        sc.close();
    }
}