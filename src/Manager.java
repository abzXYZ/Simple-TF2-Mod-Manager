import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Manager implements Serializable {
    private File customDir;
    private ArrayList<Mod> mods;
    private ArrayList<String> tags = new ArrayList<String>(Arrays.asList("model","texture","sound","vpk"));
    private ArrayList<String> customTags;
    private Map<String, Boolean> config;
    private File disabledDir = new File("disabled");

    public Manager(File customDir, Map<String, Boolean> config, ArrayList<Mod> mods,ArrayList<String> tags) {
        this.customDir = customDir;
        this.mods = mods;
        this.customTags = tags;
        this.config = config;
    }

    public Manager(File customDir) {
        this.customDir = customDir;
        this.customTags = new ArrayList<String>();
        this.mods = detectMods();
        configInitializer();
    }

    private List<Mod> scanDir(File[] directory){
        List<Mod> detected = new ArrayList<>(Collections.emptyList());
        if(directory != null) {
            for(File file : directory){
                if (file.isFile() && file.getName().endsWith(".vpk")) {
                    detected.add(new VPKmod(file));
                } else if (file.isDirectory() && !file.getName().equals("workshop")){
                    detected.add(new OpenMod(file));
                }
            }
        }
        return detected;
    }
    public ArrayList<Mod> detectMods() {
        ArrayList<Mod> enabled = (ArrayList<Mod>) scanDir(customDir.listFiles());
        ArrayList<Mod> disabled = (ArrayList<Mod>) scanDir(new File("disabled").listFiles());
        for(Mod mod : disabled){
            mod.setEnabled(false);
        }
        enabled.addAll(disabled);
        return enabled;
    }

    public void rescanMods(){
        ArrayList<Mod> found = new ArrayList<>(detectMods());
        ArrayList<Mod> modsToRemove = new ArrayList<>();
        ArrayList<Mod> modsToAdd = new ArrayList<>();
        for(Mod mod : mods){
            boolean foundName = false;
            for(Mod foundMod : found){
                if(mod.getName().equals(foundMod.getName())){
                    foundName = true;
                }
            }
            if(!foundName){
                modsToRemove.add(mod);
            }
        }
        for(Mod foundMod : found){
            boolean foundName = false;
            for(Mod mod : mods){
                if(foundMod.getName().equals(mod.getName())){
                    foundName = true;
                }
            }
            if(!foundName){
                modsToAdd.add(foundMod);
            }
        }
        for(Mod mod : modsToRemove){
            mods.remove(mod);
        }
        for(Mod mod : modsToAdd){
            mods.add(mod);
        }
    }

    public void setCustomDir(File customDir) {
        this.customDir = customDir;
    }

    public File getCustomDir() {
        return customDir;
    }

    public List<Mod> getMods() {
        return mods;
    }
    public List<Mod> getAllTaggedMods(String tag) {
        ArrayList<Mod> result = new ArrayList<>();
        for(Mod mod : mods){
            if(mod.getTags().contains(tag)){
                result.add(mod);
            }
        }
        return result;
    }
    public void moveToDisabled(Mod mod, Boolean bool){
        if(!bool){
            try {
                Files.move(mod.getModFile().toPath(), disabledDir.toPath().resolve(mod.getName()));
                mod.setModFile(new File("disabled",mod.getName()));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Couldn't disable mod! (IO Exception)\nExiting...", "Critical error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        } else {
            try {
                Files.move(mod.getModFile().toPath(), customDir.toPath().resolve(mod.getName()));
                mod.setModFile(new File(customDir,mod.getName()));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Couldn't enable mod! (IO Exception)\nExiting...", "Critical error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        }
    }
    public void setEnabledAllTaggedMods(String tag, Boolean enabled){
        for(Mod mod : mods){
            if(tag.equals("All") || mod.getTags().contains(tag)){
                moveToDisabled(mod,enabled);
                mod.setEnabled(enabled);
            }
        }
        ManagerIO.saveManager(this);
    }
    public List<String> getCustomTags(){
        return customTags;
    }

    public List<String> getTags() {
        return Stream.concat(tags.stream(), customTags.stream()).toList();
    }
    public void addTag(String tag){
        this.customTags.add(tag);
        ManagerIO.saveManager(this);
    }

    public void removeTag(String tag){
        this.customTags.remove(tag);
        for(Mod mod : mods){
            if(mod.getTags().contains(tag)){
                mod.removeTag(tag);
            }
        }
        ManagerIO.saveManager(this);
    }
    public Map<String, Boolean> getConfig() {
        return config;
    }

    public void setConfigValue(String key, Boolean value){
        config.put(key,value);
    }
    private void configInitializer(){
        this.config = new HashMap<>();
        //config.put("Auto detect tags?",true);
    }
}
