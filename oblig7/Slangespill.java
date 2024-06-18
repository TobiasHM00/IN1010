import javax.swing.*;

public class Slangespill{
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e){
            System.exit(1);
        }

        new Controller();
    }
}