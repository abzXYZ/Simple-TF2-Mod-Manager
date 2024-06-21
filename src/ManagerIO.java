import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ManagerIO {
    public static void saveManager(Manager tf2mods){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tf2mods.dat"));
            oos.writeObject(tf2mods.getCustomDir());
            oos.writeObject(tf2mods.getConfig());
            oos.writeObject(tf2mods.getMods());
            oos.writeObject(tf2mods.getCustomTags());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't save mod manager data!", "Saving error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Manager getSavedManager() throws IOException{
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(new FileInputStream("tf2mods.dat"));
        try {
            File customDir = (File) ois.readObject();
            Map<String, Boolean> config = (Map<String, Boolean>) ois.readObject();
            List<Mod> mods = (List<Mod>) ois.readObject();
            ArrayList<String> tags = (ArrayList<String>) ois.readObject();

            return new Manager(customDir,config,mods,tags);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
