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
    private final JList<String> modTagList = new JList<String>(modTags);
    private final JList<String> allTagsList = new JList<String>(allTags);
    private JButton addBtn = new JButton("Add tag");
    private JButton removeBtn = new JButton("Remove tag");
    private Mod currentMod;

    public ModWindow(){

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(3,1));
        mainpanel.add(modIcon);
        mainpanel.add(title);
        mainpanel.add(statusIcon);
        mainpanel.setPreferredSize(new Dimension(600, 200));

        JPanel subpanel = new JPanel(new GridLayout(3, 2, 25, 0));
        subpanel.add(new JLabel("Available tags:"));
        subpanel.add(new JLabel("Attached tags:"));
        JScrollPane scrollAllTags = new JScrollPane(allTagsList);
        scrollAllTags.setPreferredSize(new Dimension(160,200));
        subpanel.add(scrollAllTags);
        JScrollPane scrollModTags = new JScrollPane(modTagList);
        scrollModTags.setPreferredSize(new Dimension(160,200));
        subpanel.add(scrollModTags);

        addBtn.setIcon(new ImageIcon(ImgLibrary.getImage("plus",16,16)));
        removeBtn.setIcon(new ImageIcon(ImgLibrary.getImage("minus",16,16)));

        addBtn.addActionListener(e -> {
            String tag = allTagsList.getSelectedValue();
            if(tag != null){
                if(!currentMod.getTags().contains(tag)){
                    currentMod.addTag(tag);
                    modTags.addElement(tag);
                    ManagerIO.saveManager(Main.getModManager());
                }
            }
        });
        removeBtn.addActionListener(e -> {
            String tag = modTagList.getSelectedValue();
            if(tag != null){
                currentMod.removeTag(tag);
                modTags.removeElement(tag);
                ManagerIO.saveManager(Main.getModManager());
            }
        });

        subpanel.add(addBtn);
        subpanel.add(removeBtn);

        panel.add(mainpanel);
        panel.add(subpanel);

        this.setSize(600, 500);
        this.add(panel);
    }

    public void showModInfo(Mod mod){
        currentMod = mod;
        this.setTitle(mod.getName());
        this.setIconImage(mod.getModIcon().getImage());
        this.setLocationRelativeTo(null);
        statusIcon.setIcon(mod.getModStatusIcon());
        statusIcon.setText(mod.getModStatus());
        modIcon.setIcon(mod.getModIcon());
        allTags.removeAllElements();
        modTags.removeAllElements();
        allTags.addAll(Main.getModManager().getTags());
        modTags.addAll(mod.getTags());
        title.setText("<html><center><h1>" + mod.getName() + "</h1></center></html>");
        this.setVisible(true);
    }
}
