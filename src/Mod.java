import javax.swing.*;
import java.io.Serializable;
import java.io.File;
import java.util.List;

public abstract class Mod implements Serializable {
    private String name;
    private File modFile;
    private Boolean enabled;
    private List<String> tags;

    public Mod(File file, List<String> tags, Boolean enabled) {
        this.modFile = file;
        this.name = file.getName();
        this.enabled = enabled;
        this.tags = tags;
    }

    public Mod(File file) {
        this.modFile = file;
        this.name = file.getName();
        this.enabled = true;
        this.tags = List.of();
    }

    public String getName() {
        return name;
    }

    public File getModFile() {
        return modFile;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ImageIcon getModIcon(){
        return new ImageIcon(ImgLibrary.getImage(getEnabled(),32,32));
    }

    public abstract List<String> getTags();

    public void setTags(List<String> tags){
        this.tags = tags;
    }
    public void addTag(String tag) {
        tags.add(tag);
    }
    public void removeTag(String tag) {
        tags.remove(tag);
    }

}
