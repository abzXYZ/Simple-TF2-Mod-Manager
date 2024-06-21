import javax.swing.*;
import java.io.File;
import java.util.List;

public class VPKmod extends Mod {

    public VPKmod(File file, List<String> tags, Boolean enabled) {
        super(file, tags, enabled);
    }

    public VPKmod(File file) {
        super(file);
        this.addTag("vpk");
    }

    @Override
    public ImageIcon getModIcon() {
        return new ImageIcon(ImgLibrary.getImage("gcf",64,64));
    }


}
