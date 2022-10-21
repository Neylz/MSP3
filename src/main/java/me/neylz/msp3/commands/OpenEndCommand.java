package me.neylz.msp3.commands;

import me.neylz.msp3.data.ConfigInterface;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;


public class OpenEndCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ConfigInterface.setData("allow-end-opening", !ConfigInterface.getDataBoolean("allow-end-opening"));

        if (ConfigInterface.getDataBoolean("allow-end-opening")) {
            getServer().sendMessage(Component.text("L'end est désormais ouvert!").color(TextColor.color(0, 255, 13)));
        } else {
            getServer().sendMessage(Component.text("L'end est désormais fermé.").color(TextColor.color(255, 85, 85)));
        }

        return true;
    }
}
