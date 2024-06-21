import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OpenMod extends Mod{

    public OpenMod(File file, List<String> tags, Boolean enabled) {
        super(file, tags, enabled);
    }

    public OpenMod(File file) {
        super(file);
        setEnabled(true);
        setTags(new ArrayList<String>());
        autoDetectTags();
    }

    @Override
    public ImageIcon getModIcon() {
        String icon;
        switch (getTags().get(0)) {
            case "model":
                icon = "scrap";
                break;
            case "sound":
                icon = "sound";
                break;
            case "texture":
                icon = "paint";
                break;
            default:
                icon = "unknown";
                break;
        }
        return new ImageIcon(ImgLibrary.getImage(icon,64,64));
    }

    private void addDetectedTags(Boolean model, Boolean texture, Boolean sound){
        if(model){
            addTag("model");
        }
        if(texture){
            addTag("texture");
        }
        if(sound){
            addTag("sound");
        }
    }

    public void autoDetectTags(){
        File[] subDirs = getModFile().listFiles(File::isDirectory);
        boolean hasModels = false, hasMaterials = false, hasSound = false;
        if (subDirs != null) {
            for (File subDir : subDirs) {
                String subDirName = subDir.getName();
                if (subDirName.equals("models")) {
                    hasModels = true;
                } else if (subDirName.equals("materials")) {
                    hasMaterials = true;
                } else if (subDirName.equals("sound")) {
                    hasSound = true;
                }
            }
            addDetectedTags(hasModels,hasMaterials,hasSound);
        }
    }

}
