package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import me.neylz.msp3.inventories.FactionSelection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvents implements Listener {

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.getName().equals("Neylzounet")) {
            e.joinMessage(null);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (Msp3.playerInTeam(player) == null) {
            player.openInventory(new FactionSelection().getInventory());
        }
    }

}
