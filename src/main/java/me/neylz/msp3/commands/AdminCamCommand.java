package me.neylz.msp3.commands;

import me.neylz.msp3.Msp3;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.Bukkit.getPlayer;



public class AdminCamCommand implements CommandExecutor {

    protected void toSpectate(Player player,Boolean admincamMode) {
        //step 1 check if player already in admincam mode
        //step 2 if 1 passed, execute tpBack
        //step 3 teleport unless 1 passed and set admincam mode


        if (admincamMode) { tpBack(player); } else {
            Location pos = player.getLocation();

            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".world", pos.getWorld().getName());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".x", pos.getX());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".y", pos.getY());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".z", pos.getZ());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".pitch", pos.getPitch());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".yaw", pos.getYaw());
            Msp3.getInstance().getConfig().set("admincamLocations." + player.getUniqueId() + ".gm", player.getGameMode().toString());


            Msp3.getInstance().getConfig().set("admincamModeActivated" + player.getUniqueId(), true);


            player.setGameMode(GameMode.SPECTATOR);

            player.sendMessage(ChatColor.RED + "You are now in admincam mode");
        }
    }

    protected void tpBack(Player player) {
        World w = Bukkit.getServer().getWorld(Objects.requireNonNull(Msp3.getInstance().getConfig().getString("admincamLocations." + player.getUniqueId() + ".world")));
        double x = Msp3.getInstance().getConfig().getDouble("admincamLocations."+ player.getUniqueId() +".x");
        double y = Msp3.getInstance().getConfig().getDouble("admincamLocations."+ player.getUniqueId() +".y");
        double z = Msp3.getInstance().getConfig().getDouble("admincamLocations."+ player.getUniqueId() +".z");
        float pitch= (float) Msp3.getInstance().getConfig().getDouble("admincamLocations."+ player.getUniqueId() +".pitch");
        float yaw = (float) Msp3.getInstance().getConfig().getDouble("admincamLocations."+ player.getUniqueId() +".yaw");

        String gm = Msp3.getInstance().getConfig().getString("admincamLocations." + player.getUniqueId() + ".gm");

        Msp3.getInstance().getConfig().set("admincamModeActivated" + player.getUniqueId(), false);

        player.teleport(new Location(w, x, y, z, yaw, pitch));

        switch (Objects.requireNonNull(gm)) {
            case "SURVIVAL" -> player.setGameMode(GameMode.SURVIVAL);
            case "ADVENTURE" -> player.setGameMode(GameMode.ADVENTURE);
            case "CREATIVE" -> player.setGameMode(GameMode.CREATIVE);
            case "SPECTATOR" -> player.setGameMode(GameMode.SPECTATOR);
        }

        player.sendMessage(ChatColor.GREEN + "Position and Gamemode restored");

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //check if the command is executed by a player
        if (!(sender instanceof Player player)) { return true; }


        boolean admincamMode = Msp3.getInstance().getConfig().getBoolean("admincamModeActivated" + player.getUniqueId());


        if (args.length == 0) {
            //if no args just change gamemode and save coordinates
            this.toSpectate(player, admincamMode);

        } else if (args.length == 1 && !admincamMode) { // if there is an argument, teleport player to target
            //check if the target is valid
            Player target = getPlayer(args[0]);

            if (target == null) {   // if the player haven't been found
                player.sendMessage(ChatColor.RED + "The target is invalid");

            } else {
                this.toSpectate(player, false);
                player.setSpectatorTarget(target);
            }
            return true;


        } else {  // too many args
            player.sendMessage(ChatColor.RED + "There is too many arguments specified");
            if (admincamMode) { player.sendMessage(ChatColor.RED + "You already are in admincam mode. To go back to your previous position, run §a/admincam§c without any argument."); }
            return true;
        }

        return true;
    }
}
