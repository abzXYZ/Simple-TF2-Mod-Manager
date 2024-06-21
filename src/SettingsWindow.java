import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

public class SettingsWindow extends JFrame {
    JTextField dirPath = new JTextField();
    public SettingsWindow(){
        this.setTitle("Settings");
        this.setIconImage(ImgLibrary.getImage("coffee"));
        this.setSize(600, 300);

        JPanel panel = new JPanel();

        JLabel dirLabel = new JLabel("Current game directory:");
        dirPath.setEditable(false);

        JButton newDirBtn = new JButton("Choose new directory");
        newDirBtn.addActionListener(e -> {
            Main.showSelectionWin(false);
        });

        panel.add(dirLabel);
        panel.add(dirPath);
        panel.add(newDirBtn);


        for(Map.Entry<String, Boolean> set : Main.getModManager().getConfig().entrySet()){
            panel.add(new JLabel(set.getKey()));
            JCheckBox checked = new JCheckBox();
            checked.setSelected(set.getValue());
            checked.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    boolean isChecked = e.getStateChange() == ItemEvent.SELECTED;
                    Main.getModManager().setConfigValue(set.getKey(),isChecked);
                }
            });
            panel.add(checked);
        }
        add(panel);
    }

    public void showSettings(){
        this.setLocationRelativeTo(null);
        dirPath.setText(Main.getModManager().getCustomDir().getAbsolutePath());
        this.setVisible(true);
    }
}
