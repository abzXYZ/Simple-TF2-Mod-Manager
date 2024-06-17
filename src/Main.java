import javax.swing.*;
import java.io.IOException;


public class Main {
    private static Manager modManager;

    public static void showMainWin(){

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Non-critical error - Couldn't set the Nimbus LaF.");
        }
        try {
            modManager = ManagerIO.getSavedManager();
            System.out.println("TF2 custom folder: " + modManager.getCustomDir());
            showMainWin();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't read saved config.\nCreating new...", "No configuration detected", JOptionPane.ERROR_MESSAGE);
            new GameDirSelector().setVisible(true);
        }
    }
}