import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameDirSelector extends JFrame {
    private JLabel setupText;
    private JButton dirPathBtn;
    private File selectedFile;
    public GameDirSelector()  {
        setupText = new JLabel("Please specify the TF2 game directory ([Your Steam directory]/steamapps/common/Team Fortress 2/tf)");
        dirPathBtn = new JButton("Browse");
        this.setIconImage(ImgLibrary.getImage("coffee"));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        dirPathBtn.addActionListener(e->{
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected directory: " + selectedFile);
                File customDir = new File(selectedFile, "custom");
                if (!customDir.exists() || !customDir.isDirectory()) {
                    System.out.println("'/custom' not found in the specified location. Creating...");
                    if(customDir.mkdirs()){
                        System.out.println("'/custom' subdirectory successfully created.");
                    } else {
                        dirErrorPopup();
                    }
                } else {
                    System.out.println("'/custom' found. Proceeding...");
                }
                ManagerIO.saveManager(new Manager(customDir));
                JOptionPane.showMessageDialog(null, "Game directory selected (Assuming you chose the right one).", "Setup done", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Main.setModManager(ManagerIO.getSavedManager());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Couldn't save newly created config\nExiting...", "Critical error!", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
                Main.showMainWin(); //ciekawe czy wypada tak robić... Jeżeli nie to wrócę do starej NIEFAJNEJ metody
                this.setVisible(false);
                /*
                JOptionPane.showMessageDialog(null, "Game directory selected. Please start the app again.", "Restart porfavor", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                */
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(setupText);
        panel.add(dirPathBtn);

        this.setTitle("Setup");
        this.setSize(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(panel);
    }

    private static void dirErrorPopup(){
        JOptionPane.showMessageDialog(null, "No ./custom directory detected in the specified directory.\nThere was an error creating a new one.\nExiting...", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1); //1, bo error!
    }
}
