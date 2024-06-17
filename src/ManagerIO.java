import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public final class ManagerIO {
    public static void saveManager(Manager tf2mods){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tf2mods.dat"));
            oos.writeObject(tf2mods.getCustomDir());
            oos.writeObject(tf2mods.getMods());
            oos.writeObject(tf2mods.getTags());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't save mod manager data!", "Saving error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Manager getSavedManager() throws IOException{
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(new FileInputStream("tf2mods.dat"));
        try {
            File customDir = (File) ois.readObject();
            ArrayList<Mod> mods = (ArrayList<Mod>) ois.readObject();
            ArrayList<String> tags = (ArrayList<String>) ois.readObject();
            return new Manager(customDir,mods,tags);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
