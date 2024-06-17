import javax.swing.*;
import java.io.File;
import java.util.List;

public class OpenMod extends Mod{
    public OpenMod(String name, File fileName, Boolean enabled) {
        super(name, fileName, enabled);
    }

    @Override
    public ImageIcon getModIcon(){
        return new ImageIcon(ImgLibrary.getImage("metal",64,64));
    }

    @Override
    public List getTags(){
        return List.of();
    }
}
