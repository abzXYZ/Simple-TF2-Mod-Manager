import javax.swing.*;
import java.awt.*;

public class ModsGrid extends JPanel {

    public ModsGrid(Manager manager){
        super(new GridLayout(0,4,5,5));

        for (Mod mod : manager.getMods()){
            JButton button = new JButton(mod.getName());
            button.setIcon(mod.getModIcon());
            button.setSize(new Dimension(64,64));
            add(button);
        }
    }
}
