package me.neylz.msp3.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMaxPlayers(0);
    }

}
