import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Manager manager;

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

        TagsPanel tags = new TagsPanel(mngr);

        add(jscp, BorderLayout.CENTER);
        add(tags, BorderLayout.WEST);

        this.pack();
        setSize(1200,700);
        setLocationRelativeTo(null);
    }

}
