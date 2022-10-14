package me.neylz.msp3.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvents implements Listener {

    @EventHandler
    public void onLogin(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (player.getName().equals("Neylzounet")) {
            e.quitMessage(null);
        }
    }
}
