package me.neylz.msp3.commands;

import me.neylz.msp3.Msp3;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //check if the command is executed by a player
        if (!(sender instanceof Player player)) { return true; }

        Msp3.setMaintenance(!Msp3.isMaintenance());

        if (Msp3.isMaintenance()) {
            player.getServer().sendMessage(Component.text("Le serveur est désormais en mode maintenance. Aucune connection extérieure ne sera autorisée").color(TextColor.color(255, 83, 83)));
        } else {
            player.getServer().sendMessage(Component.text("Le serveur autorise à nouveau les connexions extérieures.").color(TextColor.color(0, 255, 0)));
        }

        return true;
    }
}
