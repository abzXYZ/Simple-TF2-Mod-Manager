import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Map;

import static java.util.Map.entry;

public final class ImgLibrary {

    private static Map<String, String> icons = Map.ofEntries(
            entry("coffee","res/Coffee_Icon.png"),
            entry("metal","res/Ico_metal.png"),
            entry("crate","res/Backpack_Unlocked_Cosmetic_Crate_Multi-Class.png")
    );
    public static Image getImage(String key){
        if(icons.containsKey(key)){
            return new ImageIcon(icons.get(key)).getImage();
        }
        System.out.println("Non-critical error - Image '" + key + "' is missing");
        return null;
    }

    public static Image getImage(String key, int width, int height){
        Image result = getImage(key);
        if(result != null){
            return result.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        }
        return null;
    }
}
