package me.neylz.msp3;



import me.neylz.msp3.commands.AdminCamCommand;
import me.neylz.msp3.commands.FactionCommand;
import me.neylz.msp3.commands.tabcompletion.AdminCamCompletion;
import me.neylz.msp3.commands.tabcompletion.FactionTabCompletion;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class Msp3 extends JavaPlugin {

    public static Msp3 instance;

    public static Msp3 getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        //commands
        Objects.requireNonNull(getCommand("admincam")).setExecutor(new AdminCamCommand());
        Objects.requireNonNull(this.getCommand("admincam")).setTabCompleter(new AdminCamCompletion());
        Objects.requireNonNull(getCommand("faction")).setExecutor(new FactionCommand());
        Objects.requireNonNull(this.getCommand("faction")).setTabCompleter(new FactionTabCompletion());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Mazened Survival Plugin 3 Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Mazened Survival Plugin 3 Disabled");


    }
}
