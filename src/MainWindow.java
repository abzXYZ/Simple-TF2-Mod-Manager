import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Manager manager;
    private SettingsWindow configWin;

    public MainWindow(Manager mngr){
        this.manager = mngr;
        setTitle("Simple TF2 Mod Manager");
        this.setIconImage(ImgLibrary.getImage("coffee"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ModsGrid grid = new ModsGrid(mngr);
        JScrollPane jscp = new JScrollPane(grid);
        jscp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(new Toolbar(), BorderLayout.NORTH);
        add(jscp, BorderLayout.CENTER);
        add(new TagsPanel(mngr,grid), BorderLayout.WEST);

        this.pack();
        setSize(1200,732);
        setLocationRelativeTo(null);
    }

}
