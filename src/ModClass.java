import javax.swing.*;
import java.io.File;

public abstract class ModClass extends Mod{
    public <T> ModClass(String name, File fileName, Boolean enabled) {
        super(name, fileName, enabled);

    }



    public ImageIcon getModIcon(){
        return new ImageIcon();
    }
}
