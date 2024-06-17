import javax.swing.*;
import java.awt.*;

public class TagsPanel extends JPanel {
    public TagsPanel(Manager manager){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(160, 700));

        DefaultListModel<String> tags = new DefaultListModel<>();
        tags.add(0,"All");
        tags.addAll(manager.getTags());
        JList<String> tagList = new JList<>(tags);
        tagList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(tagList);
        scrollPane.setPreferredSize(new Dimension(160,400));

        JButton addBtn = new JButton("Create tag");
        addBtn.setIcon(new ImageIcon(ImgLibrary.getImage("plus",16,16)));
        JButton removeBtn = new JButton("Delete tag");
        removeBtn.setIcon(new ImageIcon(ImgLibrary.getImage("minus",16,16)));
        JButton enableTag = new JButton("Enable tagged");
        enableTag.setIcon(new ImageIcon(ImgLibrary.getImage(true,16,16)));
        JButton disableTag = new JButton("Disable tagged");
        disableTag.setIcon(new ImageIcon(ImgLibrary.getImage(false,16,16)));

        add(scrollPane);
        add(addBtn);
        add(removeBtn);
        add(enableTag);
        add(disableTag);
    }
}
