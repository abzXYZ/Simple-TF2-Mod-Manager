import javax.swing.*;
import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Mod implements Serializable {
    private String name;
    private File modFile;
    private Boolean enabled;
    private ArrayList<String> tags;

    public Mod(File file, List<String> tags, Boolean enabled) {
        this.modFile = file;
        this.name = file.getName();
        this.enabled = enabled;
        this.tags = new ArrayList<>(tags);
    }

    public Mod(File file) {
        this.modFile = file;
        this.name = file.getName();
        this.enabled = true;
        this.tags = new ArrayList<String>();
    }

    public void setModFile(File newFile){
        this.modFile = newFile;
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

    public ImageIcon getModStatusIcon(){
        return new ImageIcon(ImgLibrary.getImage(getEnabled(),32,32));
    }
    public String getModStatus() {
        if(getEnabled()){
            return "Enabled";
        } else {
            return "Disabled";
        }
    }

    public abstract ImageIcon getModIcon();


    public List<String> getTags(){
        return tags;
    };

    public void setTags(ArrayList<String> tags){
        this.tags = tags;
    }
    public void addTag(String tag) {
        tags.add(tag);
    }
    public void removeTag(String tag) {
        tags.remove(tag);
    }

}
