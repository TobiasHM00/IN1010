import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint{
    private int kolonner;
    private int rader;
    private Rute[][] rutearray;

    public Labyrint(String filnavn){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Filen ikke funnet");
            return;
        }
        int row = -1; //starter denne på -1 fordi første rad er ikke en del av labyrinten
        while(sc.hasNext()){
            String linje = sc.nextLine();
            if(!linje.contains("#")){
                String[] biter = linje.strip().split(" ");
                kolonner = Integer.parseInt(biter[1]);
                rader = Integer.parseInt(biter[0]);
                rutearray = new Rute[rader][kolonner];
            }else{
                String[] biter = linje.strip().split("");
                for(int i = 0; i < biter.length; i++){
                    if(biter[i].equals("#")){
                        rutearray[row][i] = new SortRute(row, i, biter[i], this);
                    }else rutearray[row][i] = new HvitRute(row, i, biter[i], this);
                }
            }
            row++;
        }

        //lager åpninger for alle kantene, antar at det aldri vil være et tilfelle for at en åpning er i et hjørne (Det kan lett legges til)
        for(int i = 0; i < rutearray.length; i++){
            for(int j = 0; j < rutearray[0].length; j++){
                if(i == 0 && (j != 0 || j != kolonner-1)){
                    if(rutearray[i][j].hentTegn().equals(".")){
                        rutearray[i][j] = new Apning(i, j, rutearray[i][j].hentTegn(), this);
                    }
                } else if((i != 0 || i != rader-1) && j == 0 ){
                    if(rutearray[i][j].hentTegn().equals(".")){
                        rutearray[i][j] = new Apning(i, j, rutearray[i][j].hentTegn(), this);
                    }
                } else if(i == rader-1 && (j != 0 || j != kolonner-1)){
                    if(rutearray[i][j].hentTegn().equals(".")){
                        rutearray[i][j] = new Apning(i, j, rutearray[i][j].hentTegn(), this);
                    }
                } else if((i != 0 || i != rader-1) && j == kolonner-1){
                    if(rutearray[i][j].hentTegn().equals(".")){
                        rutearray[i][j] = new Apning(i, j, rutearray[i][j].hentTegn(), this);
                    }
                }
            }
        }

        //setter naboene til alle rutene
        for(int i = 0; i < rutearray.length; i++){                                                                              //i for row
            for(int j = 0; j < rutearray[0].length; j++){                                                                       //j for col
                if(i == 0 && j == 0){                                                                                           //hjørne (0,0)
                    rutearray[i][j].settNaboer(null, rutearray[i+1][j], rutearray[i][j+1], null);
                } else if(i == 0 && j == (kolonner-1)){                                                                         //hjørne (0,kolonner)
                    rutearray[i][j].settNaboer(null, rutearray[i+1][j], null, rutearray[i][j-1]);
                } else if(i == rader-1 && j == 0){                                                                              //hjørne (rader,0)
                    rutearray[i][j].settNaboer(rutearray[i-1][j], null, rutearray[i][j+1], null);
                } else if(i == rader-1 && j == kolonner-1){                                                                     //hjørne (rader, kolonner)
                    rutearray[i][j].settNaboer(rutearray[i-1][j], null, null, rutearray[i][j-1]);
                } else if(i == 0 && (j != 0 || j != kolonner-1)){                                                               //alle rutene mellom hjørnene (0,0) og (0,kolonner)
                    rutearray[i][j].settNaboer(null, rutearray[i+1][j], rutearray[i][j+1], rutearray[i][j-1]);
                } else if((i != 0 || i != rader-1) && j == 0 ){                                                                 //alle rutene mellom hjørnene (0,0) og (rader,0)
                    rutearray[i][j].settNaboer(rutearray[i-1][j], rutearray[i+1][j], rutearray[i][j+1], null);
                } else if(i == rader-1 && (j != 0 || j != kolonner-1)){                                                         //alle rutene mellom hjørnene (rader,0) og (rader, kolonner)
                    rutearray[i][j].settNaboer(rutearray[i-1][j], null, rutearray[i][j+1], rutearray[i][j-1]);
                } else if((i != 0 || i != rader-1) && j == kolonner-1){                                                         //alle rutene mellom hjørnene (0, kolonner) og (rader, kolonner)
                    rutearray[i][j].settNaboer(rutearray[i-1][j], rutearray[i+1][j], null, rutearray[i][j-1]);
                } else rutearray[i][j].settNaboer(rutearray[i-1][j], rutearray[i+1][j], rutearray[i][j+1], rutearray[i][j-1]);  //resten av rutene som ikke ligger på noen kanter
            }
        }
        System.out.println(this);
    }

    public void finnUtveiFra(int rad, int kol){
        Rute start = rutearray[rad][kol];
        System.out.println("Aapninger:");
        start.finn(null);
    }

    public void rens(){
        for(int i = 0; i < rutearray.length; i++){
            for(int j = 0; j < rutearray[0].length; j++){
                rutearray[i][j].settInts(1, 0);
            }
        }
    }

    @Override
    public String toString(){
        for (int i = 0; i < rutearray.length; i++) {
            for (int j = 0; j < rutearray[0].length; j++) {
                System.out.print(rutearray[i][j] + " ");
            }
            System.out.println("");
        }
        return "";
    }
}