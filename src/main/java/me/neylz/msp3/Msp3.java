package me.neylz.msp3;



import me.neylz.msp3.commands.AdminCamCommand;
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
        //getCommand("admincam").setTabCompleter();


        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Mazened Survival Plugin 3 Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Mazened Survival Plugin 3 Disabled");


    }
}
