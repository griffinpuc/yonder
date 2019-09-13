package griffin.github.wayward;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class Wayward extends JavaPlugin{

    @Override
    public void onEnable() {

        File path = new File(this.getDataFolder() + "/");
        if(!path.exists())
            path.mkdir();

        this.getCommand("yonder").setExecutor(new Commands(path));

    }

    @Override
    public void onDisable(){

    }

}
