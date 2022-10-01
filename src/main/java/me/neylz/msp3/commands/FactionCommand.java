package me.neylz.msp3.commands;

import me.neylz.msp3.inventories.FactionSelection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FactionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;

        FactionSelection gui = new FactionSelection();
        player.openInventory(gui.getInventory());
        

        return true;
    }
}
