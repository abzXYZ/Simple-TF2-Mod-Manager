import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Map;

import static java.util.Map.entry;

public final class ImgLibrary {

    private static Map<String, String> icons = Map.ofEntries(
            entry("coffee","res/Coffee_Icon.png"),
            entry("crate","res/Backpack_Unlocked_Cosmetic_Crate_Multi-Class.png"),
            entry("hud","res/hud.png"),
            entry("paint","res/Category_icon_Paintable_items.png"),
            entry("scrap","res/scrap.png"),
            entry("sound","res/Speaker_Icon.png"),
            entry("unknown","res/Unknown.png"),
            entry("camera","res/Camera.png"),
            entry("enabled","res/P_Have.png"),
            entry("disabled","res/P_Do_not_have.png"),
            entry("plus","res/P_Want.png"),
            entry("minus","res/P_Dash.png"),
            entry("gcf","res/gcf.jpg")
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

    public static Image getImage(Boolean enabled, int width, int height){
        Image result = getImage("disabled");
        if(enabled){
            result = getImage("enabled");
        }
        return result.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
    }
}
