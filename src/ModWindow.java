import javax.swing.*;
import java.awt.*;

public class ModWindow extends JFrame {
    private final JPanel panel = new JPanel();
    private final JLabel title = new JLabel();
    private final ImageIcon img = new ImageIcon();
    private final JLabel statusIcon = new JLabel(img);
    private final ImageIcon modImg = new ImageIcon();
    private final JLabel modIcon = new JLabel(modImg);
    private DefaultListModel<String> modTags = new DefaultListModel<>();
    private DefaultListModel<String> allTags = new DefaultListModel<>();
    private final JList

    public ModWindow(){
        panel.add(title, SwingConstants.CENTER);
        panel.add(statusIcon);
        panel.add(modIcon, SwingConstants.CENTER);
        this.setSize(600, 300);
        this.add(panel);
    }

    public void showModInfo(Mod mod){
        this.setTitle(mod.getName());
        this.setIconImage(mod.getModIcon().getImage());
        this.setLocationRelativeTo(null);
        statusIcon.setIcon(mod.getModStatusIcon());
        statusIcon.setText(mod.getModStatus());
        modIcon.setIcon(mod.getModIcon());
        title.setText("<html><h1>" + mod.getName() + "</h1></html>");
        this.setVisible(true);
    }
}
