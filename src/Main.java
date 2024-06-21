import javax.swing.*;
import java.io.IOException;


public class Main {
    private static Manager modManager;

    public static void showMainWin(){
        MainWindow mainWin = new MainWindow(modManager);
        mainWin.setVisible(true);
    }

    public static void setModManager(Manager mngr){
        modManager = mngr;
    }

    public static Manager getModManager() {
        return modManager;
    }

    public static void showSelectionWin(Boolean select){
        new GameDirSelector(select).setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Non-critical error - Couldn't set the Nimbus LaF.");
        }
        try {
            setModManager(ManagerIO.getSavedManager());
            System.out.println("TF2 custom folder: " + modManager.getCustomDir());
            showMainWin();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't read saved config.\nCreating new...", "No configuration detected", JOptionPane.ERROR_MESSAGE);
            showSelectionWin(true);
        }
    }
}