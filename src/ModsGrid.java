import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModsGrid extends JPanel {
    private Manager mngr;

    public ModsGrid(Manager manager){
        super(new GridLayout(0,3,5,5));
        mngr = manager;
        showMods();
    }

    public void showMods(){
        for (Mod mod : mngr.getMods()){
            JButton button = new JButton(mod.getName());
            button.setIcon(mod.getModIcon());
            button.setSize(new Dimension(32,32));
            add(button);
        }
    }

    public void showMods(String tag){
        for (Mod mod : mngr.getMods()){
            if(mod.getTags().contains(tag)){
                JButton button = new JButton(mod.getName());
                button.setIcon(mod.getModIcon());
                button.setSize(new Dimension(32,32));
                add(button);
            }
        }
    }
}
