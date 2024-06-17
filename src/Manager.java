import java.io.File;
import java.io.Serializable;
import java.util.*;

public class Manager implements Serializable {
    private File customDir;
    private ArrayList<Mod> mods;
    private ArrayList<String> tags;

    public Manager(File customDir, ArrayList<Mod> mods,ArrayList<String> tags) {
        this.customDir = customDir;
        this.mods = mods;
        this.tags = tags;
    }

    public File getCustomDir() {
        return customDir;
    }

    public ArrayList<Mod> getMods() {
        return mods;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
