import javax.swing.*;
import java.awt.*;

public class TagsPanel extends JPanel {
    public TagsPanel(Manager manager){
        super(new FlowLayout());
        DefaultListModel<String> tags = new DefaultListModel<>();
        tags.addAll(manager.getTags());
        JList<String> tagList = new JList<>(tags);
        JScrollPane scrollPane = new JScrollPane(tagList);
        add(scrollPane);
    }
}
