import javax.swing.*;
import java.awt.*;

public class TagsPanel extends JPanel {
    JList<String> tagList;
    public TagsPanel(Manager manager, ModsGrid modsGrid){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(160, 700));
        DefaultListModel<String> tags = new DefaultListModel<>();
        tags.add(0,"All");
        tags.addAll(manager.getTags());
        tagList = new JList<>(tags);
        tagList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(tagList);
        scrollPane.setPreferredSize(new Dimension(160,400));

        tagList.addListSelectionListener(e -> {
            modsGrid.clearGrid();
            if(tagList.getSelectedValue() != null){
                if(tagList.getSelectedValue().equals("All")){
                    modsGrid.showMods(manager.getMods());
                } else {
                    modsGrid.showMods(manager.getAllTaggedMods(tagList.getSelectedValue()));
                }
            }
        });

        JButton addBtn = new JButton("Create tag");
        addBtn.setIcon(new ImageIcon(ImgLibrary.getImage("plus",16,16)));
        addBtn.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog("Enter new tag");
            if(newTag != null && !newTag.trim().isEmpty()){
                boolean goodToGo = true;
                for(String tag : manager.getTags()){
                    if(tag.equals(newTag)){
                        goodToGo = false;
                    }
                }
                if(goodToGo && !newTag.equals("All")){
                    manager.addTag(newTag);
                    tags.addElement(newTag);
                } else {
                    JOptionPane.showMessageDialog(null, "Entered tag already exists!", "Couldn't add a new tag.", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Entered an empty tag!", "Couldn't add a new tag.", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton removeBtn = new JButton("Delete tag");
        removeBtn.setIcon(new ImageIcon(ImgLibrary.getImage("minus",16,16)));
        removeBtn.addActionListener(e -> {
            String current = tagList.getSelectedValue();
            if(current != null && !current.equals("All") && !current.equals("model") && !current.equals("texture") && !current.equals("sound")){
                manager.removeTag(tagList.getSelectedValue());
                tags.removeElement(tagList.getSelectedValue());
            } else {
                JOptionPane.showMessageDialog(null, "Can't remove a default tag!", "Couldn't remove tag.", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton enableTag = new JButton("Enable tagged");
        enableTag.setIcon(new ImageIcon(ImgLibrary.getImage(true,16,16)));
        enableTag.addActionListener(e -> {
            if(tagList.getSelectedValue() != null){
                manager.setEnabledAllTaggedMods(tagList.getSelectedValue(),true);
            }
        });
        JButton disableTag = new JButton("Disable tagged");
        disableTag.setIcon(new ImageIcon(ImgLibrary.getImage(false,16,16)));
        disableTag.addActionListener(e -> {
            if(tagList.getSelectedValue() != null){
                manager.setEnabledAllTaggedMods(tagList.getSelectedValue(),false);
            }
        });

        add(scrollPane);
        add(addBtn);
        add(removeBtn);
        add(enableTag);
        add(disableTag);
    }

    private void refreshTagList(){

    }
}
