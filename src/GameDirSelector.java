import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class GameDirSelector extends JFrame {
    private JLabel setupText;
    private JButton dirPathBtn;
    private File selectedFile;
    public GameDirSelector()  {
        setupText = new JLabel("Please specify the TF2 game directory ([Your Steam directory]/steamapps/common/Team Fortress 2/tf)");
        dirPathBtn = new JButton("Browse");

//        wybranie pliku i zapisanie go w selected file
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
                ManagerIO.saveManager(new Manager(customDir, new ArrayList<>(),new ArrayList<>()));
                //JOptionPane.showMessageDialog(null, "Game directory selected. Please start the app again.", "Restart porfavor", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Game directory selected (Assuming you chose the right one).", "Setup done", JOptionPane.INFORMATION_MESSAGE);
                Main.showMainWin(); //ciekawe czy wypada tak robić...
                System.exit(0);
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
