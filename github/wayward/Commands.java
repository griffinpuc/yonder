package griffin.github.wayward;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    File dataFolder;

    public Commands(File dataFolder){
        dataFolder = dataFolder;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("ww")){

            if(args.length == 0){

                sender.sendMessage("cheers");

            }

            else if(args[0].equalsIgnoreCase("set")){

                Location playerLocation = player.getLocation();
                String pointLabel = args[1];
                String pointX = String.valueOf(playerLocation.getX());
                String pointY = String.valueOf(playerLocation.getY());
                String pointZ = String.valueOf(playerLocation.getZ());

                try{
                    File newFile = new File("plugins/wayward/" + pointLabel);

                    FileWriter writer = new FileWriter(newFile);
                    writer.write(pointX + "\n" + pointY + "\n" + pointZ);
                    writer.close();
                }
                catch(java.io.IOException e){
                    sender.sendMessage("[WW] Error saving data. Try again.");
                    sender.sendMessage(e.getMessage());
                }

                sender.sendMessage("[WW] Waypoint " + pointLabel + " created.");


            }

            else if(args[0].equalsIgnoreCase("list")){
                String returnList = "[WW] Available waypoints";

                for(File file : new File("plugins/wayward/").listFiles()){
                    returnList = returnList + ", " + file.getName();
                }

                sender.sendMessage(returnList);
            }

            else if(args[0].equalsIgnoreCase("go")){

                List<Double> coordsList = new ArrayList<Double>();

                for (ItemStack item : player.getInventory().getContents()){
                    if (item.getType() == Material.COMPASS) {
                        for (File file : new File("plugins/wayward/").listFiles()) {
                            if (file.getName().equalsIgnoreCase(args[1])) {
                                try {
                                    String line;
                                    BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
                                    while( (line = br.readLine()) != null){
                                        coordsList.add(Double.parseDouble(line));
                                    }
                                }
                                catch (java.io.IOException e) {
                                    sender.sendMessage("[WW] Critical plugin error.");
                                    sender.sendMessage(e.getMessage());
                                }

                                Location foundLocation = new Location(player.getWorld(), coordsList.get(0), coordsList.get(1), coordsList.get(2));
                                player.setCompassTarget(foundLocation);

                                break;
                            }
                        }
                    }

                    break;
                }

                //sender.sendMessage("[WW] No compass found in inventory. Get one and try again.");


            }
        }

        return false;
    }

}
