import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private SettingsWindow configWin = new SettingsWindow();
    public Toolbar(){
        setPreferredSize(new Dimension(1200, 32));

        JButton openConfigBtn = new JButton("Settings");
        openConfigBtn.addActionListener(e->{
            configWin.showSettings();
        });

        add(openConfigBtn);
    }
}
