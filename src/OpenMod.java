import javax.swing.*;
import java.io.File;
import java.util.List;

public class OpenMod extends Mod{

    public OpenMod(File file, List<String> tags, Boolean enabled) {
        super(file, tags, enabled);
    }

    public OpenMod(File file) {
        super(file);
        setEnabled(true);
        setTags(List.of());
    }

    @Override
    public List<String> getTags() {
        return getTags();
    }

    public void autoDetectTags(){

    }

}
