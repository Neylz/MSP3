package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import me.neylz.msp3.inventories.FactionSelection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvents implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        //futur temp whitelist here
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (Msp3.playerInTeam(player) == null) {
            player.openInventory(new FactionSelection().getInventory());
        }
    }

}
