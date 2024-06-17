import javax.swing.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.io.File;
import java.util.List;
import java.util.HashMap;

public abstract class Mod implements Serializable {
    private String name;
    private File fileName;
    private Boolean enabled;
    private List<String> tags;

    public Mod(String name, File fileName, Boolean enabled) {
        this.name = name;
        this.fileName = fileName;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public File getFileName() {
        return fileName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public abstract ImageIcon getModIcon();

    public abstract List<String> getTags();
}
