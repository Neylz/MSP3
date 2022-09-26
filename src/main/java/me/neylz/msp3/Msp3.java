package me.neylz.msp3;



import me.neylz.msp3.commands.AdminCamCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;



public final class Msp3 extends JavaPlugin {


    @Override
    public void onEnable() {
        //commands
        getCommand("admincam").setExecutor(new AdminCamCommand());
        //getCommand("admincam").setTabCompleter();


        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Mazened Survival Plugin 3 Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Mazened Survival Plugin 3 Disabled");


    }
}
