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

    public Manager(File customDir, ArrayList<String> tags) {
        this.customDir = customDir;
        this.tags = tags;
        this.mods = new ArrayList<Mod>();
        lookForMods();
    }

    public void lookForMods() {
        File[] files = customDir.listFiles();
        if(files != null) {
            for(File file : files){
                if (file.isFile() && file.getName().endsWith(".vpk")) {
                    mods.add(new VPKmod(file.getName(),file,true));
                } else if (file.isDirectory() && file.getName() != "workshop"){
                    mods.add(new OpenMod(file.getName(),file,true));
                }
            }
        }
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
