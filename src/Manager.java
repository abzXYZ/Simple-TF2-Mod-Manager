import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class Manager implements Serializable {
    private File customDir;
    private List<Mod> mods;
    private List<String> tags = List.of("model","material","sound");
    private List<String> customTags;

    public Manager(File customDir, List<Mod> mods,List<String> tags) {
        this.customDir = customDir;
        this.mods = mods;
        this.customTags = tags;
    }

    public Manager(File customDir) {
        this.customDir = customDir;
        this.customTags = Collections.emptyList();
        this.mods = detectMods();
    }

    public List<Mod> detectMods() {
        File[] files = customDir.listFiles();
        List<Mod> detected = new ArrayList<>(Collections.emptyList());
        if(files != null) {
            for(File file : files){
                if (file.isFile() && file.getName().endsWith(".vpk")) {
                    detected.add(new VPKmod(file));
                } else if (file.isDirectory() && file.getName() != "workshop"){
                    detected.add(new OpenMod(file));
                }
            }
        }
        return detected;
    }

    public File getCustomDir() {
        return customDir;
    }

    public List<Mod> getMods() {
        return mods;
    }
    public List<String> getCustomTags(){
        return customTags;
    }

    public List<String> getTags() {
        return Stream.concat(tags.stream(), customTags.stream()).toList();
    }
}
