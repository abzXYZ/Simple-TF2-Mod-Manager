import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Map;

import static java.util.Map.entry;

public final class ImgLibrary {

    private static Map<String, String> icons = Map.ofEntries(
            entry("coffee","res/Coffee_Icon.png"),
            entry("metal","res/Ico_metal.png")
    );
    public static Image getImage(String key){
        return new ImageIcon(icons.get(key)).getImage();
    }
}
