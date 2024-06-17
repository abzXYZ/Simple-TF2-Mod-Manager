import javax.swing.*;
import java.io.File;
import java.util.List;

public class VPKmod extends Mod {

    public VPKmod(File file, List<String> tags, Boolean enabled) {
        super(file, tags, enabled);
    }

    public VPKmod(File file) {
        super(file);
    }

    @Override
    public List getTags(){
        return List.of("vpk");
    }

}
