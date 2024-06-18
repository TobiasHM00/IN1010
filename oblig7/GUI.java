import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GUI{
    private JFrame vindu;
    private JPanel panel, knappanel, rutenett, toppanel;
    private Knapp hoyre, venstre, opp, ned, slutt;
    private JLabel lengde;
    private JLabel[][] ruter;
    private Controller controller;
    private Random rand = new Random();
    private Rettning rettning;

    public GUI(Controller c, Rettning rettning){
        controller = c;
        this.rettning = rettning;
        ruter = new JLabel[12][12];

        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setResizable(false);
        panel = new JPanel(new BorderLayout());
        vindu.add(panel);

        toppanel = new JPanel(new BorderLayout());
        panel.add(toppanel, BorderLayout.NORTH);

        lengde = new JLabel("", SwingConstants.CENTER);
        lengde.setPreferredSize(new Dimension(100,10));
        toppanel.add(lengde, BorderLayout.WEST);

        slutt = new Knapp("Slutt");
        slutt.initGUI();
        slutt.setPreferredSize(new Dimension(100,10));
        toppanel.add(slutt, BorderLayout.EAST);

        knappanel = new JPanel();
        knappanel.setLayout(new BorderLayout());

        hoyre = new Knapp("Hoyre");
        hoyre.initGUI();
        knappanel.add(hoyre, BorderLayout.EAST);

        venstre = new Knapp("Venstre");
        venstre.initGUI();
        knappanel.add(venstre, BorderLayout.WEST);

        opp = new Knapp("Opp");
        opp.initGUI();
        knappanel.add(opp, BorderLayout.NORTH);

        ned = new Knapp("Ned");
        ned.initGUI();
        knappanel.add(ned, BorderLayout.SOUTH);

        toppanel.add(knappanel, BorderLayout.CENTER);

        rutenett = lagbrett();
        panel.add(rutenett, BorderLayout.SOUTH);
        //vindu.addKeyListener(new TrykketKnapp());
        vindu.pack();
        vindu.setVisible(true);
    }

    public JPanel lagbrett(){
        JPanel brett = new JPanel(new GridLayout(12,12));
        for(int i = 0; i < 12; i++){
            for(int j = 0; j<12; j++){
                JLabel rute = new JLabel("",SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                rute.setBackground(Color.WHITE);
                rute.setFont(new Font("Calibri", Font.BOLD,16));
                rute.setPreferredSize(new Dimension(30,30));
                rute.setOpaque(true);
                brett.add(rute);
                ruter[i][j] = rute;
            }
        }
        return brett;
    }

    public void tegnSlange(Slange slange){
        Node node = slange.hent();
        int teller = 0;
        while(node != null){
            JLabel rute = ruter[node.x][node.y];
            if(teller == 0){
                rute.setText("O");
                rute.setBackground(Color.GREEN);
                rute.setForeground(Color.BLACK);
            } else{
                rute.setText("+");
                rute.setBackground(Color.GREEN);
                rute.setForeground(Color.BLACK);
            }
            node = node.neste;
            teller++;
        }

        Node fjernet = slange.hentFjernet();
        if(fjernet != null){
            ruter[fjernet.x][fjernet.y].setText("");
            ruter[fjernet.x][fjernet.y].setBackground(Color.WHITE);
        }
    }

    public void tegnSkatt(){
        int teller = 0;
        while(teller < 10){
            int x = rand.nextInt(12);
            int y = rand.nextInt(12);
            if(!ruter[x][y].getText().equals("$") || !ruter[x][y].getText().equals("O") || !ruter[x][y].getText().equals("+")){
                ruter[x][y].setText("$");
                ruter[x][y].setForeground(Color.RED);
                teller++;
            }
        }
    }

    public void tegnNySkatt(){
        int teller = 0;
        while(teller < 1){
            int x = rand.nextInt(12);
            int y = rand.nextInt(12);
            if(!ruter[x][y].getText().equals("$") || !ruter[x][y].getText().equals("O") || !ruter[x][y].getText().equals("+")){
                ruter[x][y].setText("$");
                ruter[x][y].setForeground(Color.RED);
                teller++;
            }
        }
    }

    public boolean erSkatt(int x, int y){
        JLabel rute = ruter[x][y];
        if(rute.getText().equals("$")){
            tegnNySkatt();
            controller.minkSoving();
            return true;
        }
        return false;
    }

    public void setLengde(int tall){
        lengde.setText("Lengde: " + tall);
    }

    public boolean sjekkKollisjon(int x, int y){
        if(ruter[x][y].getText().equals("+")) return true;
        return false;
    }

    public void visTaperMelding(){
        JOptionPane.showMessageDialog(vindu, "Du tapte!", "Ferdig!", JOptionPane.CLOSED_OPTION);
    }

    public void visVinnerMelding(){
        JOptionPane.showMessageDialog(vindu, "Du vant!!", "Ferdig!", JOptionPane.CLOSED_OPTION);
    }

    //Får ikke dette til å funke :(
        
    /* class TrykketKnapp extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int nokkel = e.getKeyCode();
            if(nokkel == KeyEvent.VK_LEFT && !(rettning == Rettning.OST)){
                rettning = Rettning.VEST;
                controller.settRettning(Rettning.VEST);
            }
            if(nokkel == KeyEvent.VK_UP && !(rettning == Rettning.SOR)){
                rettning = Rettning.NORD;
                controller.settRettning(Rettning.NORD);
            }
            if(nokkel == KeyEvent.VK_DOWN && !(rettning == Rettning.NORD)){
                rettning = Rettning.SOR;
                controller.settRettning(Rettning.SOR);
            }
            if(nokkel == KeyEvent.VK_RIGHT && !(rettning == Rettning.VEST)){
                rettning = Rettning.OST;
                controller.settRettning(Rettning.OST);
            }
        }
    } */

    class Knapp extends JButton{
        private String tekst;
    
        public Knapp(String s){
            tekst = s;
            setText(s);
        }
    
        public void initGUI(){
            addActionListener(new Action());
        }
    
        private class Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                if(tekst.equals("Slutt")){
                    System.exit(1);
                } else if(tekst.equals("Hoyre") && !(rettning == Rettning.VEST)){
                    rettning = Rettning.OST;
                    controller.settRettning(Rettning.OST);
                } else if(tekst.equals("Venstre") && !(rettning == Rettning.OST)){
                    rettning = Rettning.VEST;
                    controller.settRettning(Rettning.VEST);
                } else if(tekst.equals("Opp") && !(rettning == Rettning.SOR)){
                    rettning = Rettning.NORD;
                    controller.settRettning(Rettning.NORD);
                } else if(tekst.equals("Ned") && !(rettning == Rettning.NORD)){
                    rettning = Rettning.SOR;
                    controller.settRettning(Rettning.SOR);
                }
            }
        }
    }
}