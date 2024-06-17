import javax.swing.*;
import java.awt.*;

public class ModsGrid extends JPanel {

    public ModsGrid(Manager manager){
        super(new GridLayout(0,4,5,5));

        for (Mod mod : manager.getMods()){
            JButton button = new JButton();
            button.setIcon(mod.getModIcon());
            button.setPreferredSize(new Dimension(100,100));
            add(button);
        }
    }
}
