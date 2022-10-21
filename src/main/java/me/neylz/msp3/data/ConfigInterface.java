package me.neylz.msp3.data;

import me.neylz.msp3.Msp3;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterface {
    private static Msp3 instance() { return Msp3.getInstance(); }
    private static YamlConfiguration config;
    private static File configFile;


    public static @Nullable Object getData (String path) {
        return config.get(path);
    }
    public static boolean getDataBoolean (String path) {
        return config.getBoolean(path);
    }

    public static void setData (String path, @NotNull Object value) {
        config.set(path, value);
        try {
            config.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().warning("[MSP3] Error occurred while saving config file :\n" + e.getMessage());
        }

    }


    public static void setupData() {
        configFile = new File(instance().getDataFolder() + "/" + "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        //default values here
        Map<String, Object> configDefault = new HashMap<>();

        configDefault.put("allow-end-opening", true);
        configDefault.put("custom-whitelist", false);


        if (!configFile.exists()) { //create file
            instance().getLogger().info("[MSP3] Creating config file...");

            for (var entry : configDefault.entrySet()) {    //setup entries
                config.set(entry.getKey(), entry.getValue());
            }
        } else {
            for (var entry : configDefault.entrySet()) {    //paste values if doesn't exists
                if (!config.contains(entry.getKey())) {
                    config.set(entry.getKey(), entry.getValue());
                }
            }
        }

        try {       //save the file
            config.save(configFile);
            instance().getLogger().info("[MSP3] Config file saved");
        } catch (IOException e) {
            instance().getLogger().warning("[MSP3] An error occurred while saving the config file :\n" + e.getMessage());
        }
    }
}
