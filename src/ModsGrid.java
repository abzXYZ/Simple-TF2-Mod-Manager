import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModsGrid extends JPanel {
    private Manager mngr;
    private ModWindow modWin;

    public ModsGrid(Manager manager){
        super(new GridLayout(0,3,5,5));
        mngr = manager;
        modWin = new ModWindow();
        showMods(mngr.getMods());
    }

    public void showMods(List<Mod> mods){
        for (Mod mod : mods){
            JButton button = new JButton(mod.getName());
            button.setIcon(mod.getModStatusIcon());
            button.setSize(new Dimension(32,32));
            button.addActionListener(e->{
                modWin.showModInfo(mod);
            });
            add(button);
        }
        if(mods.size()<31){
            for(int i = 0; i < 31 - mods.size(); i++){
                add(new JLabel(""));
            }
        }
    }

    public void clearGrid(){
        this.removeAll();
        this.revalidate();
        //this.repaint();
    }
}
