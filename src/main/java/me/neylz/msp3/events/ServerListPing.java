package me.neylz.msp3.events;

import me.neylz.msp3.data.ConfigInterface;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        e.setMaxPlayers(0);

        if (ConfigInterface.getDataBoolean("custom-whitelist")) {
            e.motd(Component.text("Le serveur es actuellement en maintenance").color(TextColor.color(255, 85, 85)));
        }

    }

}
