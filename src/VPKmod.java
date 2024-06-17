import java.io.File;
import java.util.List;

public class VPKmod extends Mod {

    public VPKmod(String name, File fileName, Boolean enabled) {
        super(name, fileName, enabled);
    }

    @Override
    public String getModIcon(){
        return "Xd";
    }

    @Override
    public List getTags(){
        return List.of("vpk");
    }

}
