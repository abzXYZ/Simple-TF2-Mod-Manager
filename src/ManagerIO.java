import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class ManagerIO {
    public static void saveManager(Manager tf2mods){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tf2mods.dat"));
            oos.writeObject(tf2mods.getCustomDir());
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
            List<Mod> mods = (List<Mod>) ois.readObject();
            List<String> tags = (List<String>) ois.readObject();
            return new Manager(customDir,mods,tags);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
