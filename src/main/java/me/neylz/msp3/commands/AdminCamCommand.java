package me.neylz.msp3.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getPlayer;

public class AdminCamCommand implements CommandExecutor {

    protected void toSpectate(Player player) {
        Location pos = player.getLocation();
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();

        /*
        persistentDataContainer.set(new NamespacedKey("admincam", "posx"), PersistentDataType.DOUBLE, pos.getX());
        persistentDataContainer.set(new NamespacedKey("admincam", "posy"), PersistentDataType.DOUBLE, pos.getY());
        persistentDataContainer.set(new NamespacedKey("admincam", "posz"), PersistentDataType.DOUBLE, pos.getZ());
        persistentDataContainer.set(new NamespacedKey("admincam", "world"), PersistentDataType.TAG_CONTAINER, pos.getWorld().getPersistentDataContainer());
        persistentDataContainer.set(new NamespacedKey("admincam", "pitch"), PersistentDataType.FLOAT, pos.getPitch());
        persistentDataContainer.set(new NamespacedKey("admincam", "yaw"), PersistentDataType.FLOAT, pos.getYaw());*/

        player.setGameMode(GameMode.SPECTATOR);
    }

    /*protected void tpBack(Player player) {
        PersistentDataContainer persistentDataContainer = (PersistentDataContainer) player.getPersistentDataContainer().getKeys();
        player.teleport(new Location(
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "world")),
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "posx")),
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "posy")),
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "posz")),
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "yaw")),
                persistentDataContainer.getOrDefault(new NamespacedKey("admincam", "pitch"))
        ));
    }*/

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //check if the command is executed by a player
        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;
        sender.sendMessage("Salut!");



        if (args.length == 0) {
            //if no args just change gamemode and save coordinates
            this.toSpectate(player);

        } else if (args.length == 1) { // if there is an argument, teleport player to target
            //check if the target is valid
            Player target = getPlayer(args[0]);

            if (target == null) {   // if the player haven't been found
                player.sendMessage(ChatColor.RED + "The target is invalid");

            } else {
                this.toSpectate(player);
                player.setSpectatorTarget(target);
            }
            return true;


        } else {  // too many args
            player.sendMessage(ChatColor.RED + "There is too many arguments specified");
            return true;
        }

        return true;
    }
}
