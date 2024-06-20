import javax.swing.*;
import java.awt.*;

public class ModWindow extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel title = new JLabel();
    private ImageIcon img = new ImageIcon();
    private JLabel icon = new JLabel(img);

    public ModWindow(){
        panel.setLayout(new FlowLayout());
        panel.add(title);
        panel.add(icon);
        this.setSize(600, 300);
        this.add(panel);
    }

    public void showModInfo(Mod mod){
        this.setTitle(mod.getName());
        this.setLocationRelativeTo(null);
        icon.setIcon(mod.getModIcon());
        title.setText("<html><h1>" + mod.getName() + "</h1></html>");
        this.setVisible(true);
    }
}
