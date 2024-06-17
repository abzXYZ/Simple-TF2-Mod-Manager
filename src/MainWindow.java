import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Manager manager;

    public MainWindow(Manager mngr){
        this.manager = mngr;
        setTitle("Simple TF2 Mod Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ModsGrid grid = new ModsGrid(mngr);
        add(grid, BorderLayout.CENTER);

        setSize(500,400);
        setLocationRelativeTo(null);
    }

}
